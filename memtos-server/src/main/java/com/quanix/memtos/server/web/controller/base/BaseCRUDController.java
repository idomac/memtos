package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.base.AbstractEntity;
import com.quanix.memtos.server.plugin.search.Searchable;
import com.quanix.memtos.server.service.base.BaseService;
import com.quanix.memtos.server.util.Constants;
import com.quanix.memtos.server.web.permission.PermissionList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String list(Pageable pageable,Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }

        logger.info("模型列表操作:"+entityClass.getSimpleName());

        model.addAttribute("page",baseService.findAll(pageable));
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


    /**
     * 查看浏览
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model,@PathVariable("id") ID id) {

        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }
        M m =  baseService.findOne(id);
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "查看");
        return viewName("edit");
    }


    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") ID id, Model model) {
        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }
        M m =  baseService.findOne(id);
        model.addAttribute(Constants.OP_NAME, "修改");
        model.addAttribute("m", m);
        setCommonData(model);
        return viewName("edit");
    }


    /**
     * 更新操作
     * @param model
     * @param m
     * @param result
     * @param backURL
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(Model model,@Valid @ModelAttribute("m") M m,BindingResult result,
        @RequestParam(value = Constants.BACK_URL, required = false) String backURL,@PathVariable("id") ID id,
        RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }

        if (hasError(m, result)) {
            return showUpdateForm(id,model);
        }
        baseService.save(m);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "修改成功");
        return redirectToUrl(backURL);
    }

    /**
     * 删除表单页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") ID id,Model model) {
        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }
        model.addAttribute(Constants.OP_NAME, "删除");
        M m =  baseService.findOne(id);
        model.addAttribute("m", m);
        setCommonData(model);
        return viewName("edit");
    }


    /**
     * 提交删除操作
     * @param model
     * @param m
     * @param result
     * @param backURL
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public String delete(Model model,@Valid @ModelAttribute("m") M m,BindingResult result,
     @RequestParam(value = Constants.BACK_URL, required = false) String backURL,@PathVariable("id") ID id,
     RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }
        baseService.delete(id);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }


    /**
     * 批量删除操作
     * @param ids
     * @param backURL
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "batch/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInBatch(
            @RequestParam(value = "ids", required = false) ID[] ids,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }
        baseService.delete(ids);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }
}
