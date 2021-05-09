package com.example.springboot.moudle.test.Test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.HashMap;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/5/9
 */
@Slf4j
public class TestJson implements Serializable {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age","18");
        map.put("address","广东省广州市");

        //子节点
        HashMap<String, Object> childrenMap = new HashMap<>();
        childrenMap.put("name","李四");
        childrenMap.put("age","19");
        childrenMap.put("address","广东省广州市");

        //子节点2
        HashMap<String, Object> childrenMap1 = new HashMap<>();
        childrenMap1.put("name","王五");
        childrenMap1.put("age","20");
        childrenMap1.put("address","广东省广州市");
        childrenMap.put("children",childrenMap1);

        // 外层map操作
        map.put("children",childrenMap);

        jsonObject.put("data",map);

        //System.out.println(jsonObject);

        Object tree = getTree(jsonObject.getJSONObject("data"));

        System.out.println(JSONObject.toJSON(tree));
    }

    // 递归处理

    public static Object  getTree(JSONObject jsonObject){

        HashMap<String, Object> map = new HashMap<>();
        String name = jsonObject.getString("name");
        String age = jsonObject.getString("age");
        String address = jsonObject.getString("address");
        map.put("jsonName",name);
        map.put("jsonAge",age);
        map.put("jsonAdress",address);
        JSONObject children = jsonObject.getJSONObject("children");
        if (children != null ){
            log.info("进入循环中的json{}",children);
            map.put("children",getTree(children));
        }
        return  map;
    }
}
