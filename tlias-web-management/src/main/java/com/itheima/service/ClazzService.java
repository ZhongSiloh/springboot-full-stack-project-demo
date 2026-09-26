package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void delete(Integer id);

    void add(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);


    List<Clazz> findAll();
}
