package com.zzx.service;

import com.github.pagehelper.PageInfo;
import com.zzx.bean.Book;
import com.zzx.mapper.book.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;


public interface BookService {

    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(Book record) throws Exception;

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> getAll();

    PageInfo<Book> getPage(int pageNum, int pageSize);
}
