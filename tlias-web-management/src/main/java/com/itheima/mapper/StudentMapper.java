package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);


    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    @Select("select * from student where id=#{id}")
    Student getInfo(Integer id);

    void update(Student student);

    @Update("update student set violation_score=violation_score+#{score},violation_count=#{violationCount} where id=#{id}")
    void violation(Integer id, Integer score, Short violationCount);


    List<Map<String, Object>> countStuClazzsData();


    List<Map<String, Object>> countStuDegreeData();
}
