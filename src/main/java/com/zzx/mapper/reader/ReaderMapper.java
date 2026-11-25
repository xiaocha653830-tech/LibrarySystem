package com.zzx.mapper.reader;

import com.zzx.bean.Reader;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author masionx
* @description 针对表【reader(读者表)】的数据库操作Mapper
* @createDate 2026-11-17 20:17:38
* @Entity com.zzx.example.entity.Reader
*/

public interface ReaderMapper {


    @Delete("DELETE FROM reader WHERE id = #{id}")
    int deleteByPrimaryKey(@Param("id") Integer id);

    @Insert("""
    INSERT INTO reader
    (name, phone, status)
    VALUES
    (#{name}, #{phone}, #{status})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Reader record);

    @Insert("""
    <script>
        INSERT INTO reader
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="name != null">name,</if>
            <if test="phone != null">phone,</if>
            <if test="status != null">status,</if>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="name != null">#{name},</if>
            <if test="phone != null">#{phone},</if>
            <if test="status != null">#{status},</if>
        </trim>
    </script>
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(Reader record);


    @Select("SELECT * FROM reader WHERE id = #{id}")
    Reader selectByPrimaryKey(@Param("id") Integer id);


    @Update("""
    <script>
        UPDATE reader
        <set>
            <if test="name != null">name = #{name},</if>
            <if test="phone != null">phone = #{phone},</if>
            <if test="status != null">status = #{status},</if>
        </set>
        WHERE id = #{id}
    </script>
    """)
    int updateByPrimaryKeySelective(Reader record);


    @Update("""
    UPDATE reader
    SET name = #{name},
        phone = #{phone},
        status = #{status}
    WHERE id = #{id}
    """)
    int updateByPrimaryKey(Reader record);

    @Select("SELECT * FROM reader ORDER BY id ASC")
    List<Reader> getAll();

}
