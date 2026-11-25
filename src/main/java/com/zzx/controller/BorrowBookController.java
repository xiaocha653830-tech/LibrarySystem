package com.zzx.controller;


import com.zzx.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> borrowBook(
            @PathVariable Integer recordId){
        borrowBookService.returnBook(recordId);

        return ResponseEntity.ok("归还成功");
    }
}
