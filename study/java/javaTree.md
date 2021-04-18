## javaTree



#### javaTree 学习

> 基础对象类

```java
package cn.sinvie.modules.test;

import lombok.Data;

import java.util.ArrayList;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/18
 */

@Data
public class Dept {

    private Integer id;
    private String name;

    private int prentId;

    private ArrayList<Dept> children = new ArrayList<>();
}

```



> 递归获取树

```java
package cn.sinvie.modules.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/18
 */
@Slf4j
public class TestMap {

    public static void main(String[] args) {
        Object treeChildren = getTreeChildren();
        String s = JSON.toJSONString(treeChildren);
        log.info(s);
    }


    public static Object getTreeChildren(){
        ArrayList<Dept> depts = new ArrayList<>();

        Dept dept = new Dept();
        dept.setId(1);
        dept.setName("公司研发总部");
        dept.setPrentId(0);
        depts.add(dept);

        Dept dept1 = new Dept();
        dept1.setId(2);
        dept1.setName("公司研发广州分部1");
        dept1.setPrentId(1);
        depts.add(dept1);

        Dept dept2 = new Dept();
        dept2.setId(3);
        dept2.setName("公司财务部门");
        dept2.setPrentId(0);
        depts.add(dept2);

        //子部门
        Dept dept3 = new Dept();
        dept3.setId(4);
        dept3.setName("公司财务部门广州分部");
        dept3.setPrentId(3);
        depts.add(dept3);

        //子部门2
        Dept dept4 = new Dept();
        dept4.setId(5);
        dept4.setName("公司财务部门广州分部2");
        dept4.setPrentId(3);
        depts.add(dept4);

        // 获取顶级目录
        ArrayList<Dept> prirentDept = new ArrayList<>();
        depts.stream().forEach(x ->{
            if(x.getPrentId()== 0){
                prirentDept.add(x);
            }
        });

        Object tree = getTree(prirentDept, depts);

        return tree;
    }

    /**
     * 获取目录树
     * @param parentList
     * @param allList
     * @return
     */
    public static Object  getTree(List<Dept> parentList,List<Dept> allList){
        parentList.stream().forEach(x -> {
            allList.stream().forEach(y ->{
                if(x.getId() == y.getPrentId()){
                    x.getChildren().add(y);
                    ArrayList<Dept> depts = new ArrayList<>(1);
                    depts.add(y);
                    getTree(depts,allList);
                }
            });
        });

        return parentList;
    }
}

```



总结简单 写一个 递归树