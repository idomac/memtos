package com.quanix.memtos.server.dao.repository;

import com.quanix.memtos.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * 基础存储仓库
 *
 */
@NoRepositoryBean
public interface BaseRepository<M,ID extends Serializable> extends JpaRepository<M,ID> {

    public M findById(Long id);

    public long count();
}
