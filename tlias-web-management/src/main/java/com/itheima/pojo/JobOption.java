package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//职位统计图：后端将统计图所需的数据封装为JSON，传递给前端
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOption {
    private List jobList;//各职位
    private List dataList;//各职位对应的人数
}
