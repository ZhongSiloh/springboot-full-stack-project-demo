package com.itheima.controller;

import com.aliyun.core.annotation.Path;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clazzs")
@Slf4j
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    //分页查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询:{}",clazzQueryParam);

        PageResult<Clazz> pageResult =clazzService.page(clazzQueryParam);

        return Result.success(pageResult);
    }

//    删除班级
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id){
        clazzService.delete(id);
        return Result.success();
    }

//    添加班级
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级:{}",clazz);
        clazzService.add(clazz);
        return Result.success();
    }
    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        Clazz clazz=clazzService.getInfo(id);
        return Result.success(clazz);
    }
    //修改班级
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

//   查询所有班级
    @GetMapping("/list")
    public Result findAll(){
        List<Clazz> clazzList=clazzService.findAll();
        return Result.success(clazzList);
    }



}
