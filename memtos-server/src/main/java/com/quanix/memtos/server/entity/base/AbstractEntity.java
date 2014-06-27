package com.quanix.memtos.server.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author : lihaoquan
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    protected ID id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
