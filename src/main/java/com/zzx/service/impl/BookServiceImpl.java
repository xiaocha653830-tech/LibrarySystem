package com.zzx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.bean.Book;
import com.zzx.mapper.book.BookMapper;
import com.zzx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int deleteByPrimaryKey(Long id) throws SQLException {
        return bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Book record) throws SQLException {
        return bookMapper.insert(record);
    }

    @Override
    public int insertSelective(Book record) {
        return bookMapper.insertSelective(record);
    }

    @Override
    public Book selectByPrimaryKey(Long id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return bookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Book> getPage(int pageNum, int pageSize) {

        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 紧接着执行的第一个查询会被分页
        List<Book> bookList = bookMapper.getAll();

        // 封装分页信息
        return new PageInfo<>(bookList);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.getAll();
    }
}
