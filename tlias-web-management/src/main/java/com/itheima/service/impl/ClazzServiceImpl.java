package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    //分页查询
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());

        List<Clazz> clazzList =clazzMapper.list(clazzQueryParam);
        clazzList.forEach(clazz -> {
            LocalDate currentTime=LocalDate.now();
            if (currentTime.isAfter(clazz.getEndDate())){
                clazz.setStatus("已结课");
            }else if (currentTime.isBefore(clazz.getBeginDate())){
                clazz.setStatus("未开班");
            }else {
                clazz.setStatus("在读中");
            }
        });

        Page<Clazz> p=(Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(),p.getResult());

    }

    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
