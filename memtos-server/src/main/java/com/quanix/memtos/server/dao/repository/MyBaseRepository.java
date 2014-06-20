package com.quanix.memtos.server.dao.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * created by lihaoquan
 *
 * 自定义的仓库基类抽象:
 *
 * 可以把这个类想象成一个 BaseRepository 的代理, BaseRepository是抽象,不带实现的
 *
 * 例如,我在 BaseRepository 增加一个"特有的"方法抽象
 * 那么可以在这里对这个"特有的"方法进行实现
 *
 * printModel(M m) 是例子
 */
public class MyBaseRepository<M , ID extends Serializable> extends SimpleJpaRepository<M, ID> implements BaseRepository<M,ID> {


    public MyBaseRepository(JpaEntityInformation<M, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public MyBaseRepository(Class<M> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public List<M> findAll() {
        System.out.println("[全部查找]");
        return super.findAll();
    }

    @Override
    public void printModel(M m) {
        System.out.println(m.getClass().getName());
    }
}
