package com.zzx.service;

import com.zzx.bean.BorrowRecord;

public interface BorrowBookService {

     void borrowBook(Integer bookId, Integer readerId);

     void returnBook(BorrowRecord borrowRecord);
}
