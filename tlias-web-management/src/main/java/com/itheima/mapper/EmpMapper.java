package com.itheima.mapper;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//员工信息
@Mapper
public interface EmpMapper {
    //原始分页查询
    /*
    //分页查询：总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    public long count();

    //分页查询：每页的数据
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
            " order by e.entry_date desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);
    */

    //PageHelper实现分页查询，条件分页查询
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +" order by e.entry_date desc")
    public List<Emp> list(EmpQueryParam empQueryParam);


    //新增员工
    @Options(useGeneratedKeys = true ,keyProperty = "id") //主键返回：为true则代表，现在要使用数据库生成的主键；keyProperty来将获取的主键注入Emp的某个属性
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime}) ")
    void insert(Emp emp);

    //批量删除员工
    void deleteByIds(List<Integer> ids);

    //根据id查询员工信息（查询回显）
    Emp getById(Integer id);

//    修改更新员工信息
    void updateById(Emp emp);

//    为ECharts统计图统计员工职位数据
    List<Map<String, Object>> countEmpJobData();

//    为ECharts统计图统计员工性别人数
    List<Map<String, Object>> countEmpGenderData();

//    查询全部员工
    @Select("select * from emp where job=1")
    List<Emp> selectAll();

//    根据用户名和密码查询员工信息
    @Select("select id,username,name from emp where username=#{username} and password=#{password} ")
    Emp selectByUsernameAndPassword(Emp emp);
}
