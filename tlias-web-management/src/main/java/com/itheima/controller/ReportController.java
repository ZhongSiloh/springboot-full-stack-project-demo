package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.pojo.StudentClazzsOption;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//处理ECharts统计图统计员工职位数据的Controller
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

//    统计员工职位人数
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");

        JobOption jobOption =reportService.getEmpJobData();

        return Result.success(jobOption);
    }
//  统计员工性别人数
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");

        List<Map<String,Object>> genderList =reportService.getEmpGenderData();

        return Result.success(genderList);
    }

//    统计班级人数
    @GetMapping("/studentCountData")
    public Result studentCountData(){

        StudentClazzsOption studentClazzsOption=reportService.getstudentCountData();

        return Result.success(studentClazzsOption);
    }
//    学员学历统计
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData(){
        List<Map<String,Object>> degreeList=reportService.getStuDegreeData();

        return Result.success(degreeList);
    }

}

