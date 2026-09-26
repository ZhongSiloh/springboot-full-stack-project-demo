package com.itheima.mapper;


import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//员工工作经历
@Mapper
public interface EmpExprMapper {


    //批量保存工作经历信息（插入0，1或多条数据，使用动态SQL）
    void insertBatch(List<EmpExpr> exprList);

    //批量删除工作经历
    void deleteByEmpIds(List<Integer> empIds);
}
