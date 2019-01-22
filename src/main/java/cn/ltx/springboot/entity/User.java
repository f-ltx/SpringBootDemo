package cn.ltx.springboot.entity;

import java.util.List;

/**
 * Description: 用户表
 * @author      litianxiang
 * @date        2019/1/22
 */
public class User {
    private Long id;
    private String username;
    private String password ;
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
