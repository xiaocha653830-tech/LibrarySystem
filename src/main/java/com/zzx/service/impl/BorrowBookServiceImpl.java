package com.zzx.service.impl;

import com.zzx.bean.BorrowRecord;
import com.zzx.bean.Reader;
import com.zzx.exception.BookNotFoundException;
import com.zzx.exception.BusinessException;
import com.zzx.mapper.book.BookMapper;
import com.zzx.mapper.borrowBook.BorrowBookMapper;
import com.zzx.mapper.reader.ReaderMapper;
import com.zzx.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    @Autowired
    BorrowBookMapper borrowBookMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    ReaderMapper readerMapper;



    @Override
    @Transactional
    public void borrowBook(Integer bookId, Integer readerId) {
        if (bookId == null) {
            throw new BusinessException("图书 ID 不能为空");
        }
        if (readerId == null) {
            throw new BusinessException("读者 ID 不能为空");
        }
        //检查读者是否存在
        Reader reader = readerMapper.selectByPrimaryKey(readerId);
        if(reader == null){
            throw new BusinessException("读者不存在");
        }
        if (!"正常".equals(reader.getStatus())) {
            throw new BusinessException("该读者当前无法借阅");
        }
        //检查该读者是否已经借阅且未归还
        int count = borrowBookMapper.countUnreturnedRecord(bookId, readerId);
        if (count > 0 ){
            throw new BusinessException("该读者已经借阅此书，尚未归还");
        }

        int rows = bookMapper.decreaseAvailableCount(bookId);
        if (rows == 0) {
            throw new BookNotFoundException("图书不存在或库存不足");
        }

        BorrowRecord record = new BorrowRecord();
        record.setBookId(bookId);
        record.setReaderId(readerId);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(30));

        int result = borrowBookMapper.insertBorrowRecord(record);
        if (result == 0) {
            throw new BusinessException("借阅记录创建失败");
        }
    }

    @Override
    @Transactional
    public void returnBook(Integer borrowRecordId) {
        if (borrowRecordId == null) {
            throw new BusinessException("借阅记录 ID 不能为空");
        }
        BorrowRecord record = borrowBookMapper.getByRecordId(borrowRecordId);
        if (record == null){
            throw new BusinessException("借阅记录不存在");
        }

        if (record.getReturnDate() != null) {
            throw new BusinessException("该图书已经归还");
        }

        String date = String.valueOf(LocalDate.now());
        int row = borrowBookMapper.updateReturnDate(record.getId(), date);
        if (row == 0){
            throw new BusinessException("借阅记录更新失败");
        }

        int i = bookMapper.increaseAvailableCount(record.getBookId());
        if (i == 0){
            throw new BusinessException("借阅信息有误");
        }

    }

    @Override
    public List<BorrowRecord> getByReaderId(Integer readerId) {
        return borrowBookMapper.getByReaderId(readerId);
    }

    @Override
    public List<BorrowRecord> getUnreturnedRecords(Integer readerId) {
        return borrowBookMapper.getUnreturnedRecords(readerId);
    }

    @Override
    public List<BorrowRecord> getByBookId(Integer bookId) {
        return borrowBookMapper.getByBookId(bookId);
    }

    @Override
    public List<BorrowRecord> getAllRecords() {
        return borrowBookMapper.getAll();
    }
}
