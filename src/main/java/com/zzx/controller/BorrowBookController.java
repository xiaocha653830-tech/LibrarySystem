package com.zzx.controller;


import com.zzx.bean.BorrowRecord;
import com.zzx.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowBookController {


    @Autowired
    private BorrowBookService borrowBookService;

    @PostMapping("/{bookId}/{readerId}")
    public ResponseEntity<String> borrowBook(
            @PathVariable Integer bookId,
            @PathVariable Integer readerId){
        borrowBookService.borrowBook(bookId,readerId);

        return ResponseEntity.ok("借阅成功");
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<String> returnBook(
            @PathVariable Integer recordId){
        borrowBookService.returnBook(recordId);

        return ResponseEntity.ok("归还成功");
    }

    //根据readerId查询借阅记录
    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<BorrowRecord>> getRecordByReader(
            @PathVariable Integer readerId){
        List<BorrowRecord> recordList = borrowBookService.getByReaderId(readerId);
        return ResponseEntity.ok(recordList);

    }

    //根据bookId查询借阅记录
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowRecord>> getRecordByBook(
            @PathVariable Integer bookId){
        List<BorrowRecord> byBookId = borrowBookService.getByBookId(bookId);
        return ResponseEntity.ok(byBookId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BorrowRecord>> getAll(){
        List<BorrowRecord> allRecords = borrowBookService.getAllRecords();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/unreturned/{readerId}")
    public ResponseEntity<List<BorrowRecord>> getUnreturnedRecords(
            @PathVariable Integer readerId){
        List<BorrowRecord> unreturnedRecords = borrowBookService.getUnreturnedRecords(readerId);
        return ResponseEntity.ok(unreturnedRecords);
    }
}
