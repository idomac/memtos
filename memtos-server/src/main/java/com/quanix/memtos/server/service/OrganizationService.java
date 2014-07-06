package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.OrganizationDao;
import com.quanix.memtos.server.entity.Organization;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class OrganizationService extends BaseService<Organization,Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 自动装载OrganizationDao
     * @return
     */
    @Autowired
    private OrganizationDao getOrganizationDao() {
        return (OrganizationDao) baseRepository;
    }


    /**
     * 创建组织架构
     * @param organization
     * @return
     */
    public Organization createOrganization(Organization organization) {

        final String sql = "insert into s_organization( name, parent_id, parent_ids, available) values(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();


        final String org_name =  organization.getName();
        final Long org_parId =  organization.getParentId();
        final String org_parIds = organization.getParentIds();
        final boolean org_avail = organization.getAvailable();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, org_name);
                psst.setLong(count++, org_parId);
                psst.setString(count++, org_parIds);
                psst.setBoolean(count++, org_avail);
                return psst;
            }
        }, keyHolder);
        organization.setId(keyHolder.getKey().longValue());
        return organization;
    }


    public void deleteOrganization(Long organizationId) {
        Organization organization = findOne(organizationId);
        final String deleteSelfSql = "delete from s_organization where id=?";
        jdbcTemplate.update(deleteSelfSql, organizationId);
        final String deleteDescendantsSql = "delete from s_organization where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, organization.makeSelfAsParentIds() + "%");
    }



    public Organization updateOrganization(Organization organization) {
        final String sql = "update s_organization set name=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                organization.getName(), organization.getParentId(), organization.getParentIds(), organization.getAvailable(), organization.getId());
        return organization;
    }

}
