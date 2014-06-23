package com.quanix.memtos.server.web.permission;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * created by lihaoquan
 */
public class PermissionList implements Serializable {

    public static final String CREATE_PERMISSION = "create";
    public static final String UPDATE_PERMISSION = "update";
    public static final String DELETE_PERMISSION = "delete";
    public static final String VIEW_PERMISSION = "view";

    /**
     * 资源前缀
     */
    private String resourceIdentity;

    /**
     * key:    权限
     * value : 资源
     */
    private Map<String, String> resourcePermissions = Maps.newHashMap();


    /**
     * 自动生成 create update delete 的权限串
     *
     * @param resourceIdentity
     * @return
     */
    public static PermissionList newPermissionList(String resourceIdentity) {

        PermissionList permissionList = new PermissionList();

        permissionList.resourceIdentity = resourceIdentity;

        permissionList.resourcePermissions.put(CREATE_PERMISSION, resourceIdentity + ":" + CREATE_PERMISSION);
        permissionList.resourcePermissions.put(UPDATE_PERMISSION, resourceIdentity + ":" + UPDATE_PERMISSION);
        permissionList.resourcePermissions.put(DELETE_PERMISSION, resourceIdentity + ":" + DELETE_PERMISSION);
        permissionList.resourcePermissions.put(VIEW_PERMISSION, resourceIdentity + ":" + VIEW_PERMISSION);

        return permissionList;
    }

}
