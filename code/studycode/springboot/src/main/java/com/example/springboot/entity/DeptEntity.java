package com.example.springboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/20
 */
@Data
public class DeptEntity implements Serializable {


    private String name;

    private Long id;

    private String  address;


    private String fax;
    private String responseName;

    private String  phone;


    private Long pid;

}
