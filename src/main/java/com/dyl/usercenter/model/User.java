package com.dyl.usercenter.model;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
