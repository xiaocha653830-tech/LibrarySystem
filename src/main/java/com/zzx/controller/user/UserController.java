package com.zzx.controller.user;

import com.github.pagehelper.PageInfo;
import com.zzx.bean.Book;
import com.zzx.bean.BorrowRecord;
import com.zzx.mapper.book.BookMapper;
import com.zzx.service.BookService;
import com.zzx.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    BookService bookService;
    @Autowired
    BorrowBookService borrowBookService;

    //分页查询
    @GetMapping("/books")
    public ResponseEntity<PageInfo<Book>> getPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize){
        if (pageNum < 1 || pageSize < 1 || pageSize > 100) {
            return ResponseEntity.badRequest().build();
        }
        PageInfo<Book> page = bookService.getPage(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }

    //查询自己的借阅记录
    @GetMapping("/{readerId}/borrow-records")
    public ResponseEntity<List<BorrowRecord>> getBorrowRecords(
            @PathVariable Integer readerId){
        List<BorrowRecord> records = borrowBookService.getByReaderId(readerId);
        return ResponseEntity.ok(records);
    }


    //查询图书详情
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(
            @PathVariable Long bookId){
        Book book = bookService.selectByPrimaryKey(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    //借书
    @PostMapping("/{readerId}/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(
            @PathVariable Integer readerId,
            @PathVariable Integer bookId){
        borrowBookService.borrowBook(bookId,readerId);
        return ResponseEntity.ok("借阅成功");
    }

    //还书
    @PutMapping("/{readerId}/borrow/{recordId}/return")
    public ResponseEntity<String> returnBook(
            @PathVariable Integer recordId){
        borrowBookService.returnBook(recordId);
        return ResponseEntity.ok("归还成功");
    }

    //查询未归还的记录
    @GetMapping("/{readerId}/borrow/unreturned")
    public ResponseEntity<List<BorrowRecord>>  getUnreturnedRecords(
            @PathVariable Integer readerId){
        List<BorrowRecord> unreturnedRecords = borrowBookService.getUnreturnedRecords(readerId);
        return ResponseEntity.ok(unreturnedRecords);
    }
}
