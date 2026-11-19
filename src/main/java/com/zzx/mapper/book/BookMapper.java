package com.zzx.mapper.book;

import com.zzx.bean.Book;
import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;

/**
* @author masionx
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2026-11-17 20:13:04
* @Entity com.zzx.example.entity.Book
*/
@Mapper
public interface BookMapper {

    int deleteByPrimaryKey(Long id) throws SQLException;

    int insert(Book record) throws SQLException;

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

}
