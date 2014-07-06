package com.quanix.memtos.server.web.taglib;

import com.quanix.memtos.server.entity.*;
import com.quanix.memtos.server.service.*;
import com.quanix.memtos.server.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * created by lihaoquan
 *
 * Tag lib 功能类
 */
public class Functions {

    private static OrganizationService organizationService;
    private static ResourceService resourceService;
    private static UserService userService;
    private static AppService appService;
    private static RoleService roleService;

    public static String testOutput(Long id) {
        return "测试输出["+id+"]";
    }


    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }


    public static boolean inString(String str, Object element) {
        if(str == null) {
            return false;
        }

        String[] strarray = str.split(",");
        if(strarray.length==0) {
            return false;
        }
        return Arrays.asList(strarray).contains(element);

    }


    public static String organizationName(Long organizationId) {
        Organization organization = getOrganizationService().findOne(organizationId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }


    public static String resourceName(Long resourceId) {
        Resource resource = getResourceService().findOne(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(String resourceIdstr) {
        if(StringUtils.isEmpty(resourceIdstr)) {
            return "";
        }
        List<String> resourceIds = Arrays.asList(resourceIdstr.split(","));

        StringBuilder s = new StringBuilder();
        for(String resourceId : resourceIds) {
            Resource resource = getResourceService().findOne(Long.parseLong(resourceId));
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }


    public static String appName(Long appId) {
        App app = getAppService().findOne(appId);
        if(app == null) {
            return "";
        }
        return app.getName();
    }

    public static String username(Long userId) {
        User user = getUserService().findOne(userId);
        if(user == null) {
            return "";
        }
        return user.getUsername();
    }

    public static String roleName(Long roleId) {
        Role role = getRoleService().findOne(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(Collection<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            Role role = getRoleService().findOne(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }


    public static OrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(OrganizationService.class);
        }
        return organizationService;
    }

    public static ResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }

    public static UserService getUserService() {
        if(userService == null) {
            userService = SpringUtils.getBean(UserService.class);
        }
        return userService;
    }

    public static AppService getAppService() {
        if(appService == null) {
            appService = SpringUtils.getBean(AppService.class);
        }
        return appService;
    }

    public static RoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

}
