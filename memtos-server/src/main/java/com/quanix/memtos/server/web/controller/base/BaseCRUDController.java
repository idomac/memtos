package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.entity.base.AbstractEntity;

import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * 基于业务的增删改查控制器
 */
public abstract class BaseCRUDController<M extends AbstractEntity,ID extends Serializable>
        extends BaseController<M,ID> {


}
