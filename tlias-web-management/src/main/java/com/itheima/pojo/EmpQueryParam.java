package com.itheima.pojo;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//接收Emp的分页条件查询的前端参数为一个对象
@Data
public class EmpQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate end;

}
