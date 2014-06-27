package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.entity.base.AbstractEntity;
import com.quanix.memtos.server.plugin.entity.Treeable;
import com.quanix.memtos.server.web.permission.PermissionList;

import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * 基本树形控制器
 */
public class BaseTreeableController<M extends AbstractEntity<ID> & Treeable<ID>, ID extends Serializable>
        extends BaseController<M,ID> {

    protected PermissionList permissionList = null;
}
