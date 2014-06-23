package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.base.AbstractEntity;
import com.quanix.memtos.server.service.base.BaseService;
import com.quanix.memtos.server.web.permission.PermissionList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * 基于业务的增删改查控制器
 */
public abstract class BaseCRUDController<M extends AbstractEntity,ID extends Serializable>
        extends BaseController<M,ID> {

    private static Logger logger = LoggerFactory.getLogger(BaseCRUDController.class);


    @Autowired
    protected BaseService<M,ID> baseService;

    private boolean listAlsoSetCommonData = false;

    protected PermissionList permissionList = null;


    /**
     * 列表也设置common data
     */
    public void setListAlsoSetCommonData(boolean listAlsoSetCommonData) {
        this.listAlsoSetCommonData = listAlsoSetCommonData;
    }

    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("模型列表操作");
        model.addAttribute("dataList", baseService.findAll());
        return viewName("list");
    }

}
