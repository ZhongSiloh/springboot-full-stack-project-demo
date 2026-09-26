package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    //本地存储方案
    /*
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收到的参数：{},{},{}",name,age,file);

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取原始文件的后缀
        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID生成随机字符，防止文件名在本机重复，导致保存失败
        String newFileName= UUID.randomUUID().toString()+extension;

        //指定位置保存文件
        file.transferTo(new File("D:/DocumantFiles/图片/"+newFileName));

        return Result.success();
    }
    */

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    //阿里云OSS文件上传
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());

        //调用文件上传工具类
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS后的url：{}",url);

        return Result.success(url);

    }

}
