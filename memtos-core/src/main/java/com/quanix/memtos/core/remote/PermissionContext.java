package com.quanix.memtos.core.remote;

import java.util.Set;

/**
 * @author : lihaoquan
 */
public class PermissionContext {

    private Set<String> roles;

    private Set<String> permissions;

    @Override
    public String toString() {
        return "PermissionContext{" +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
