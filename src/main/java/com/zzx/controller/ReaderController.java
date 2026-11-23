package com.zzx.controller;

import com.github.pagehelper.PageInfo;
import com.zzx.bean.Reader;
import com.zzx.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByPrimaryKey(@PathVariable Integer id) throws Exception {
        int rows = readerService.deleteByPrimaryKey(id);
        if (rows > 0) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("读者不存在，删除失败");
        }
    }

    @PostMapping
    public ResponseEntity<String> insertReader(@RequestBody Reader reader) throws Exception {
        int insert = readerService.insert(reader);
        if (insert > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("添加成功");
        } else {
            return ResponseEntity.badRequest()
                    .body("添加失败");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> selectOne(@PathVariable Integer id) {
        Reader reader = readerService.selectByPrimaryKey(id);
        if (reader != null) {
            return ResponseEntity.ok(reader);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("读者不存在");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOne(@RequestBody Reader reader,@PathVariable Integer id){
        reader.setId(Math.toIntExact(id));
        int rows = readerService.updateByPrimaryKeySelective(reader);
        if (rows > 0) {
            return ResponseEntity.ok("修改成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("修改失败");
        }
    }

    @GetMapping
    public ResponseEntity<List<Reader>> getAll() {
        List<Reader> readerList = readerService.getAll();
        return ResponseEntity.ok(readerList);
    }

    @GetMapping("/page")
    public ResponseEntity<PageInfo<Reader>> getPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {

        if (pageNum < 1 || pageSize < 1 || pageSize > 100) {
            return ResponseEntity.badRequest().build();
        }

        PageInfo<Reader> pageInfo = readerService.getPage(pageNum, pageSize);

        return ResponseEntity.ok(pageInfo);
    }
}
