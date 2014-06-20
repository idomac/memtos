package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.entity.base.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author : lihaoquan
 */
@Entity
@Table(name="s_user")
public class User extends AbstractEntity {

    private String username;

    @Column(name = "organization_id")
    private Long organizationId; //所属公司

    private String password;

    private String salt;

    private String status;

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

    @Transient
    public String getCredentialsSalt() {
        return username + salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
