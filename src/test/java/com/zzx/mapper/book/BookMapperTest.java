package com.zzx.mapper.book;

import com.zzx.bean.Book;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void checkResourcePath() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:com/zzx/mapper/*.xml");
        System.out.println("======== 实际匹配到的 XML 文件 ========");
        for (Resource resource : resources) {
            System.out.println(resource.getFilename() + " -> 存在：" + resource.exists());
        }
    }

    @Test
    void printMyBatisConfig() {
        System.out.println("====== MyBatis 加载的 XML 列表 ======");
        sqlSessionFactory.getConfiguration().getMappedStatements().forEach(ms -> {
            // 过滤掉系统默认的方法，只看我们自己的
            if (ms.getId().contains("BookMapper")) {
                System.out.println(ms.getId());
            }
        });

        System.out.println("====== 是否找到 deleteByPrimaryKey ======");
        boolean hasDelete = sqlSessionFactory.getConfiguration()
                .hasStatement("com.zzx.mapper.book.BookMapper.deleteByPrimaryKey");
        System.out.println("结果: " + hasDelete);
    }

    @Test
    void deleteByPrimaryKey() throws SQLException {
        bookMapper.deleteByPrimaryKey(8L);
    }


    @Test
    void insert() throws SQLException {
        Book book = new Book();
        book.setIsbn("123456");
        book.setTitle("测试图书");
        book.setAuthor("测试作者");
        book.setPublisher("测试出版社");
        book.setTotalCount(5);
        book.setAvailableCount(5);
        System.out.println(bookMapper.insert(book));
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }
}