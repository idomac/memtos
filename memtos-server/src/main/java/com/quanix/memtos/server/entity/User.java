package com.quanix.memtos.server.entity;

import java.io.Serializable;

/**
 * @author : lihaoquan
 */
public class User implements Serializable {

    private Long id;

    private Long organizationId; //所属公司

    private String username;

    private String password;

    private String salt;

    public User() {

    }

    public User(String username,String password) {

        this.username = username ;
        this.password = password ;

    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
