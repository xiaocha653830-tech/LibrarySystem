package com.zzx.mapper.book;

import com.zzx.bean.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
* @author masionx
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2026-11-17 20:13:04
* @Entity com.zzx.example.entity.Book
*/
@Repository
public interface BookMapper {

    @Delete("DELETE FROM book WHERE id = #{id}")
    int deleteByPrimaryKey(@Param("id") Long id) throws SQLException;

    @Insert("""
        INSERT INTO book
        (isbn, title, author, publisher, total_count, available_count)
        VALUES
        (#{isbn}, #{title}, #{author}, #{publisher},
         #{totalCount}, #{availableCount})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Book record) throws SQLException;

    @Insert("""
    <script>
        INSERT INTO book
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="isbn != null">isbn,</if>
            <if test="title != null">title,</if>
            <if test="author != null">author,</if>
            <if test="publisher != null">publisher,</if>
            <if test="totalCount != null">total_count,</if>
            <if test="availableCount != null">available_count,</if>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="isbn != null">#{isbn},</if>
            <if test="title != null">#{title},</if>
            <if test="author != null">#{author},</if>
            <if test="publisher != null">#{publisher},</if>
            <if test="totalCount != null">#{totalCount},</if>
            <if test="availableCount != null">#{availableCount},</if>
        </trim>
    </script>
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(Book record);


    @Select("SELECT * FROM book WHERE id = #{id}")
    Book selectByPrimaryKey(@Param("id") Long id);

    @Update("""
    <script>
        UPDATE book
        <set>
            <if test="isbn != null">isbn = #{isbn},</if>
            <if test="title != null">title = #{title},</if>
            <if test="author != null">author = #{author},</if>
            <if test="publisher != null">publisher = #{publisher},</if>
            <if test="totalCount != null">total_count = #{totalCount},</if>
            <if test="availableCount != null">available_count = #{availableCount},</if>
        </set>
        WHERE id = #{id}
    </script>
    """)
    int updateByPrimaryKeySelective(Book record);

    @Update("""
    UPDATE book
    SET isbn = #{isbn},
        title = #{title},
        author = #{author},
        publisher = #{publisher},
        total_count = #{totalCount},
        available_count = #{availableCount}
    WHERE id = #{id}
    """)
    int updateByPrimaryKey(Book record);

    @Select("SELECT * FROM book ORDER BY id ASC")
    List<Book> getAll();

}
