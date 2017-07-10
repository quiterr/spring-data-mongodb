package com.study.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;

/**
 * Created by Floki on 2017/6/16.
 */
@TypeAlias("user")
public class User {

    private Long userId;
    private String account;
    @Id
    private String name;
    private Integer age;
    private String password;
    private Project project;
    private Object extend;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
