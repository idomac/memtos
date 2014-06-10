package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.entity.base.AbstractEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * 基于业务的增删改查控制器
 */
public class BaseCRUDController<M extends AbstractEntity,ID extends Serializable> extends BaseController<M,ID> {


}
