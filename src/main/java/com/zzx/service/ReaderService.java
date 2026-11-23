package com.zzx.service;

import com.github.pagehelper.PageInfo;
import com.zzx.bean.Book;
import com.zzx.bean.Reader;

import java.util.List;

public interface ReaderService {

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Reader record) throws Exception;

    int insertSelective(Reader record);

    Reader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reader record);

    int updateByPrimaryKey(Reader record);

    List<Reader> getAll();

    PageInfo<Reader> getPage(int pageNum, int pageSize);
}
