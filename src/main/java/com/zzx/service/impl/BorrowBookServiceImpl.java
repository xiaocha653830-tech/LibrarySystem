package com.zzx.service.impl;

import com.zzx.bean.BorrowRecord;
import com.zzx.mapper.book.BookMapper;
import com.zzx.mapper.borrowBook.BorrowBookMapper;
import com.zzx.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    @Autowired
    BorrowBookMapper borrowBookMapper;
    @Autowired
    BookMapper bookMapper;

    public BorrowBookServiceImpl(BorrowBookMapper borrowBookMapper, BookMapper bookMapper) {
        this.borrowBookMapper = borrowBookMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public void borrowBook(Integer bookId, Integer readerId) {
        //1. 检查该读者是否已经借阅且未归还
        int count = borrowBookMapper.countUnreturnedRecord(bookId, readerId);
        if (count > 0 ){
            throw new RuntimeException("该读者已经借阅此书，尚未归还");
        }

        int rows = bookMapper.decreaseAvailableCount(bookId);
        if (rows == 0) {
            throw new RuntimeException("图书不存在或库存不足");
        }

        BorrowRecord record = new BorrowRecord();
        record.setBookId(bookId);
        record.setReaderId(readerId);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(30));

        int result = borrowBookMapper.insertBorrowRecord(record);
        if (result == 0) {
            throw new RuntimeException("借阅记录创建失败");
        }
    }

    @Override
    @Transactional
    public void returnBook(BorrowRecord borrowRecord) {

    }
}
