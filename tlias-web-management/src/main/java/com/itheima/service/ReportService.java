package com.itheima.service;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentClazzsOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    StudentClazzsOption getstudentCountData();

    List<Map<String, Object>> getStuDegreeData();
}
