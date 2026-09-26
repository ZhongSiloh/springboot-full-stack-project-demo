package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//接收Clazz的分页条件查询的前端参数为一个对象
@Data
public class ClazzQueryParam {
    private String name;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate end;
    private Integer page=1;
    private Integer pageSize=10;
}
