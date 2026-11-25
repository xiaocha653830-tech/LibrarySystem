package com.zzx.controller;

import com.github.pagehelper.PageInfo;
import com.zzx.bean.Book;
import com.zzx.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByPrimaryKey(@PathVariable Long id) throws Exception {
        int rows = bookService.deleteByPrimaryKey(id);
        if (rows > 0) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("图书不存在，删除失败");
        }
    }

    @PostMapping
    public ResponseEntity<String> insertBook(@Valid @RequestBody Book book) throws Exception {
        int insert = bookService.insert(book);
        if (insert > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("添加失败");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> selectOne(@PathVariable Long id) {
        Book book = bookService.selectByPrimaryKey(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("图书不存在");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOne(@RequestBody Book book,@PathVariable Long id){
        book.setId(Math.toIntExact(id));
        int rows = bookService.updateByPrimaryKeySelective(book);
        if (rows > 0) {
            return ResponseEntity.ok("修改成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("修改失败");
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> bookList = bookService.getAll();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/page")
    public ResponseEntity<PageInfo<Book>> getPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {

        // 参数校验
        if (pageNum < 1 || pageSize < 1 || pageSize > 100) {
            return ResponseEntity.badRequest().build();
        }

        PageInfo<Book> pageInfo = bookService.getPage(pageNum, pageSize);

        return ResponseEntity.ok(pageInfo);
    }
}
