package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//无需创建EmpExpr实体类的Service层，EmpExpr员工工作经历附属于Emp，均由EmpService操作
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;//在事务的传播行为中，方法都要被事务控制，而事务的注解加在Service层，所以这里注入的是Service而不是Mapper


    //原始分页查询
    /*
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.分页查询：总记录数
        long total = empMapper.count();
        //2.分页查询：每页的数据
        Integer start=(page-1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.封装为PageResult
        return new PageResult<Emp>(total,rows);
    }
     */
    //PageHelper实现分页查询
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查询结果，并封装
        Page<Emp> p= (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    //新增员工
    @Transactional(rollbackFor = {Exception.class})//进行Spring业务管理
    @Override
    public void save(Emp emp) {
        try {
            //1.保存基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存工作经历信息
            //需要用到emp在刚刚插入数据到数据库后数据库自动生成的主键id（即EmpExpr中的empId），则保存基本信息insert要主键返回
            List<EmpExpr> exprList = emp.getExprList(); //emp获取工作经历，List集合（0/1/多个）
            if (!CollectionUtils.isEmpty(exprList)){    //工具类CollectionUtils.isEmpty判断集合为空

                //遍历集合，为同一个员工（empId）的不同工作经历EmpExpr赋值empId
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志，无论操作成功还是失败，放在finally（需要empLogService的insertLog注解事务，并指定为总是创建新事务，而不是加入当前事务，防止因当前事务进行回滚操作而记录失败）
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }
    }


    //批量删除
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {

        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.批量删除工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

//    根据id查询员工信息（查询回显）
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

//    修改更新员工信息
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {

        //1.根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据id修改员工工作经历（先删再加）
        //2.1先删
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2再加
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

//    查询全部员工
    @Override
    public List<Emp> findAll() {
        return empMapper.selectAll();
    }

//    登录
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码
        Emp e =empMapper.selectByUsernameAndPassword(emp);
        //2.判断是否存在这个员工，存在则组装 登录信息
        if (e!=null) {
            log.info("登录成功：{}",e);

            //生成token
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt=JwtUtils.generateToken(claims);


            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt );
        }
        //3.不存在，返回null
        return null;
    }
}
