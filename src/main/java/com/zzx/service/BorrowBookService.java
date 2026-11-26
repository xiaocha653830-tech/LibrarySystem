package com.zzx.service;

import com.zzx.bean.BorrowRecord;

import java.util.List;

public interface BorrowBookService {

     void borrowBook(Integer bookId, Integer readerId);

     void returnBook(Integer borrowRecordId);

     List<BorrowRecord> getByReaderId(Integer readerId);

     List<BorrowRecord> getUnreturnedRecords(Integer readerId);

     List<BorrowRecord> getByBookId(Integer bookId);

     List<BorrowRecord> getAllRecords();

}
