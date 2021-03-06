package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.entity.base.AbstractEntity;

import javax.persistence.*;

/**
 * created by lihaoquan
 */
@Entity
@Table(name = "s_resource")
public class Resource extends AbstractEntity<Long> {

    private String name; //资源名称
    private ResourceType type = ResourceType.menu; //资源类型
    private String url; //资源路径
    private String permission; //权限字符串
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Boolean available = Boolean.FALSE;


    public static enum ResourceType {
        menu("菜单"), button("按钮");
        private final String info;
        private ResourceType(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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


    @Transient
    public boolean isRootNode() {
        return parentId == 0;
    }

    @Transient
    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
}
