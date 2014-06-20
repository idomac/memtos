package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.entity.base.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by lihaoquan
 */
@Entity
@Table(name = "s_role")
public class Role extends AbstractEntity {

    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private String resourceIds;
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
