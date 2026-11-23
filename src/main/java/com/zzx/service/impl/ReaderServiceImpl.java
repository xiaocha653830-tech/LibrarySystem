package com.zzx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.bean.Reader;
import com.zzx.mapper.reader.ReaderMapper;
import com.zzx.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    ReaderMapper readerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return readerMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int insert(Reader record) throws Exception {
        return readerMapper.insert(record);
    }

    @Override
    public int insertSelective(Reader record) {
        return readerMapper.insertSelective(record);
    }

    @Override
    public Reader selectByPrimaryKey(Integer id) {
        return readerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Reader record) {
        return readerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Reader record) {
        return readerMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Reader> getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<Reader> readerList = readerMapper.getAll();

        return new PageInfo<>(readerList);
    }

    @Override
    public List<Reader> getAll() {
        return readerMapper.getAll();
    }


}