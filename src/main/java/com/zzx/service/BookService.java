package com.zzx.service;

import com.zzx.bean.Book;


import java.sql.SQLException;


public interface BookService {


    int deleteByPrimaryKey(Long id) throws SQLException;

    int insert(Book record) throws SQLException;

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

}
