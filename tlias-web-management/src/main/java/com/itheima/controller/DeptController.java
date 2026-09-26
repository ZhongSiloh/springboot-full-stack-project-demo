package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {


    @Autowired
    private DeptService deptService;

    //查询全部部门数据
    @GetMapping("/depts")
    public Result list(){
//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList= deptService.findAll();

        return Result.success(deptList);
    }

    //根据id删除部门
    @Log
    @DeleteMapping("/depts")
    public Result delete(Integer id){
//        System.out.println("Delete dept by id："+id);
        log.info("Delete dept by id："+id);

        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
//        System.out.println("新增部门："+dept);
        log.info("新增部门："+dept);
        deptService.add(dept);

        return Result.success();
    }

    //根据ID查询部门
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
//        System.out.println("根据ID查询部门:"+id);
        log.info("根据ID查询部门:"+id);

        Dept dept =deptService.getById(id);

        return Result.success(dept);
    }

    //修改部门
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门"+dept);
        log.info("修改部门"+dept);
        deptService.update(dept);

        return Result.success();
    }
}
