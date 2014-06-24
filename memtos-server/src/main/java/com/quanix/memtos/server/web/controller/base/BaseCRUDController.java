package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.base.AbstractEntity;
import com.quanix.memtos.server.service.base.BaseService;
import com.quanix.memtos.server.util.Constants;
import com.quanix.memtos.server.web.permission.PermissionList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("模型列表操作:"+entityClass.getSimpleName());
        model.addAttribute("dataList", baseService.findAll());
        return viewName("list");
    }


    /**
     * 进入新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {

        //判断是否有创建的权限
        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        logger.info("新增操作:"+entityClass.getSimpleName());
        setCommonData(model);
        model.addAttribute(Constants.OP_NAME, "新增");

        if (!model.containsAttribute("m")) {
            model.addAttribute("m", newModel());
        }
        return viewName("edit");
    }

    /**
     * 在新增页面进行提交操作
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create( Model model, @Valid @ModelAttribute("m") M m, BindingResult result,
         RedirectAttributes redirectAttributes) {
        logger.info("新增保存操作"+entityClass.getSimpleName());

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }
        if (hasError(m, result)) {
            return showCreateForm(model);
        }
        baseService.save(m);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "新增成功");
        return redirectToUrl(null);
    }
}
