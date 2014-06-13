package com.quanix.memtos.server.dao.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * 自定义的仓库基类抽象
 */
public class MyBaseRepository<M , ID extends Serializable> extends SimpleJpaRepository<M, ID> implements BaseRepository<M,ID> {


    public MyBaseRepository(JpaEntityInformation<M, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public MyBaseRepository(Class<M> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
