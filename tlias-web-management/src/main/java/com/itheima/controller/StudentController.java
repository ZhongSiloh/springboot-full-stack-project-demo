package com.itheima.controller;


import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //分页查询
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询:{}",studentQueryParam);

        PageResult<Student> pageResult=studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    //删除学员
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        studentService.deleteByIds(ids);

        return Result.success();
    }

    //添加学员
    @PostMapping
    public Result add(@RequestBody Student student){
        studentService.add(student);
        return Result.success();
    }

    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Student student =studentService.getInfo(id);

        return Result.success(student);
    }
    //更新员工
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);

        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable("id") Integer id,@PathVariable("score") Integer score){
        studentService.violation(id,score);

        return Result.success();
    }

}
