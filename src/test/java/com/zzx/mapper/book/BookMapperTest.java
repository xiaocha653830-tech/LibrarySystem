package com.zzx.mapper.book;

import com.zzx.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.SQLException;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;


    @Test
    void deleteByPrimaryKey() throws SQLException {

    }

    @Test
    void insert() throws SQLException {

    }

    @Test
    void selectByPrimaryKey() {

    }

    @Test
    void updateByPrimaryKeySelective() {
    }
}