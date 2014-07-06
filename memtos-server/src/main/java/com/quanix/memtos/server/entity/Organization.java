package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.entity.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * created by lihaoquan
 */
@Entity
@Table(name = "s_organization")
public class Organization extends AbstractEntity<Long> {

    private String name; //组织机构名称
    private Long parentId; //父编号
    private String parentIds; //父编号列表，如1/2/
    private Boolean available = Boolean.FALSE;

    @Transient
    public boolean isRootNode() {
        return parentId == 0;
    }

    @Transient
    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
