package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.entity.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by lihaoquan
 *
 */
@Entity
@Table(name = "s_app")
public class App extends AbstractEntity<Long> {

    private String name;

    private String app_key;

    private String app_secret;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }
}
