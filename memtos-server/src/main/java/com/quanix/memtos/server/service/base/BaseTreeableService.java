package com.quanix.memtos.server.service.base;

import com.quanix.memtos.server.dao.repository.support.RepositoryHelper;
import com.quanix.memtos.server.entity.base.AbstractEntity;
import com.quanix.memtos.server.plugin.entity.Treeable;
import com.quanix.memtos.server.util.ReflectUtils;

import java.io.Serializable;

/**
 * created by lihaoquan
 */
public abstract class BaseTreeableService<M extends AbstractEntity<ID> & Treeable<ID>,ID extends Serializable>
        extends BaseService<M,ID> {


    private RepositoryHelper repositoryHelper;

    public BaseTreeableService() {
        Class<M> entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
        repositoryHelper = new RepositoryHelper(entityClass);
        String entityName = repositoryHelper.getEntityName(entityClass);
    }

}
