package com.example.springboot.Test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.entity.DeptEntity;

import javax.xml.soap.DetailEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/21
 * 练习下递归
 */
public class DgTest {
    public static void main(String[] args) {
        List<DeptEntity> list = getList();
        ArrayList<Map<String, Object>> maps = new ArrayList<>();

        List<DeptEntity> collect = list.stream().filter(x -> x.getPid() == 0).collect(Collectors.toList());

        for (DeptEntity deptEntity : collect) {
            maps.add(getTree(deptEntity,list));
        }
        System.out.println(JSON.toJSONString(maps));
    }


    public static List<DeptEntity> getList(){
        ArrayList<DeptEntity> deptEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DeptEntity deptEntity = new DeptEntity();
            deptEntity.setName("研发部"+i);
            deptEntity.setId((long)i+1L);

            deptEntity.setPid((long)0);

            if(i==2 ){
                deptEntity.setPid((long)1);
            }
            if(i ==4){
                deptEntity.setPid((long) i);
            }
            deptEntities.add(deptEntity);
        }

        return deptEntities;
    }

    /**
     * 目录树结构
     *
     */
    public static Map<String, Object> getTree(DeptEntity deptEntity ,List<DeptEntity> list){


        HashMap<String, Object> map = new HashMap<>();
        map.put("name",deptEntity.getName());
        map.put("pid",deptEntity.getPid());
        map.put("id",deptEntity.getId());

        map.put("address",deptEntity.getAddress());
        ArrayList<Map<String, Object>> Lists = new ArrayList<>();
        for (DeptEntity entity : list) {
            if(deptEntity.getId().equals(entity.getPid())){
               Lists.add(getTree(entity,list));
            }
        }
        map.put("children",Lists);
        return map;

    }
}
