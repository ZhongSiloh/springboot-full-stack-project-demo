package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentClazzsOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();//Map：pos=职位，num=人数

        //2.组装数据，返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public StudentClazzsOption getstudentCountData() {
        List<Map<String,Object>> list=studentMapper.countStuClazzsData();

        List<Object> clazzsList=list.stream().map(dataMap ->dataMap.get("pos")).toList();
        List<Object> dataList=list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new StudentClazzsOption(clazzsList,dataList);
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return studentMapper.countStuDegreeData();
    }
}
