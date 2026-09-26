package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    private static Short violationCount=0;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());

        List<Student> studentList=studentMapper.list(studentQueryParam);

        Page<Student> p=(Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);

    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        violationCount++;
        studentMapper.violation(id,score,violationCount);
    }
}
