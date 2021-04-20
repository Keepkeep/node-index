package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.example.springboot.entity.DeptEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/20
 */

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/testFiler")
    public Object  testFastjson(){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setId(1L);
        deptEntity.setName("研发部门");
        deptEntity.setAddress("广州市天河区");
        deptEntity.setFax("808089");
        deptEntity.setResponseName("张三");
        deptEntity.setPhone("123344445");


        SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter(deptEntity.getClass(), "phone", "fax");
        String s = JSON.toJSONString(deptEntity, simplePropertyPreFilter);
        return s;
    }
}
