package com.example.springboot.Test;

import com.example.springboot.entity.DeptEntity;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/20
 */
public class TestBean {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setName("张三");
        deptEntity.setPhone("12635783789");
        String name = org.apache.commons.beanutils.BeanUtils.getProperty(deptEntity, "name");
        System.out.println(name);
    }
}
