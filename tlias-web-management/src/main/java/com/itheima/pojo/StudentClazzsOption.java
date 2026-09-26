package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//封装每一个班级的人数
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClazzsOption {
    private List clazzsList;
    private List dataList;
}
