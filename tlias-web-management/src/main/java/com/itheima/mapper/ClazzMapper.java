package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id =#{id}")
    void delete(Integer id);

    @Insert("insert into clazz(name,room,create_time,update_time,master_id,subject,begin_date,end_date)" +
            " values (#{name},#{room},#{createTime},#{updateTime},#{masterId},#{subject},#{beginDate},#{endDate})")
    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> findAll();
}
