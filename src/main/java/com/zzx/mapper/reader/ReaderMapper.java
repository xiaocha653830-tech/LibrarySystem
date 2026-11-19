package com.zzx.mapper.reader;

import com.zzx.bean.Reader;
import org.apache.ibatis.annotations.Mapper;

/**
* @author masionx
* @description 针对表【reader(读者表)】的数据库操作Mapper
* @createDate 2026-11-17 20:17:38
* @Entity com.zzx.example.entity.Reader
*/
@Mapper
public interface ReaderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Reader record);

    int insertSelective(Reader record);

    Reader selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Reader record);

    int updateByPrimaryKey(Reader record);

}
