package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


//员工管理Controller
@RequestMapping("/emps")
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);

        PageResult<Emp>  pageResult= empService.page(empQueryParam);//调用service层查询，返回PageResult类
        return Result.success(pageResult);//PageResult类封装为Result，返回值
    }

    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);

        empService.save(emp);
        return Result.success();
    }

    //删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.delete(ids);

        return Result.success();
    }

    //根据id查询员工信息（查询回显）
    @GetMapping("/{id}")
    public Result getInfo( @PathVariable Integer id){
        log.info("根据id查询员工信息，{}",id);

        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
    //根据查询回显，修改更新员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("员工信息:{}",emp);
        empService.update(emp);

        return Result.success();
    }

//    查询全部员工
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询全部员工");
        List<Emp> list=empService.findAll();

        return Result.success(list);

    }

}
