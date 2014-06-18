package com.quanix.memtos.server.service.base;

import com.quanix.memtos.server.dao.repository.BaseRepository;
import com.quanix.memtos.server.entity.base.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * created by lihaoquan
 *
 * 服务基类
 *
 */
public abstract class BaseService<M extends AbstractEntity,ID extends Serializable> {

    protected BaseRepository<M, ID> baseRepository;

    @Autowired
    public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public M findOne(ID id) {
        return baseRepository.findOne(id);
    }

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public long count() {
        return baseRepository.count();
    }

    public List<M> findAll() {
        return baseRepository.findAll();
    }

    public M save(M model) {
        return baseRepository.save(model);
    }

    public M saveAndFlush(M model) {
        model = save(model);
        baseRepository.flush();
        return model;
    }

    public void delete(ID id) {
        baseRepository.delete(id);
    }

    public void printModel(M m) {
        baseRepository.printModel(m);
    }
}
