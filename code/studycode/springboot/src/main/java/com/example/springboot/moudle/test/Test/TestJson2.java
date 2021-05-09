package com.example.springboot.moudle.test.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/5/9
 */
@Slf4j
public class TestJson2 implements Serializable {

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "18");
        map.put("address", "广东省广州市");

        //子节点
        HashMap<String, Object> childrenMap = new HashMap<>();
        childrenMap.put("name", "李四");
        childrenMap.put("age", "19");
        childrenMap.put("address", "广东省广州市");

        //子节点2
        HashMap<String, Object> childrenMap1 = new HashMap<>();
        childrenMap1.put("name", "王五");
        childrenMap1.put("age", "20");
        childrenMap1.put("address", "广东省广州市");
        //childrenMap.put("children",childrenMap1);

        //子节点2-1
        HashMap<String, Object> childrenMap12 = new HashMap<>();
        childrenMap12.put("name", "王五2");
        childrenMap12.put("age", "20");
        childrenMap12.put("address", "广东省广州市");
        ArrayList<HashMap<String, Object>> mapArray1 = new ArrayList<>();
        mapArray1.add(childrenMap12);
        childrenMap1.put("children",mapArray1);

        // 数据集合
        ArrayList<HashMap<String, Object>> mapArrays = new ArrayList<>();
        mapArrays.add(childrenMap);
        mapArrays.add(childrenMap1);

        // 外层map操作
        map.put("children", mapArrays);

        jsonObject.put("data", map);


        Object tree = getTree(jsonObject.getJSONObject("data"));

        System.out.println(JSONObject.toJSON(tree));
    }

    // 递归处理

    public static HashMap<String ,Object> getTree(JSONObject jsonObject) {

        HashMap<String, Object> map = new HashMap<>();
        String name = jsonObject.getString("name");
        String age = jsonObject.getString("age");

        String address = jsonObject.getString("address");
        map.put("jsonName", name);
        map.put("jsonAge", age);
        map.put("jsonAdress", address);
        JSONArray children = jsonObject.getJSONArray("children");
        if (children != null && children.size() > 0) {
            ArrayList<HashMap<String, Object>> mapArrays = new ArrayList<>();
            log.info("进入循环中的json{}", children);
            for (int i = 0; i < children.size(); i++) {
                JSONObject jsonObject1 = children.getJSONObject(i);
                HashMap<String ,Object> tree = getTree(jsonObject1);
                mapArrays.add(tree);
            }
            map.put("children",mapArrays);
        }
        return map;
    }
}
