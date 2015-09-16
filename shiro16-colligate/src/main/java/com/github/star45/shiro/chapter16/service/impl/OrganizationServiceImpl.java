/** 
* @file     OrganizationServiceImpl.java 
* @brief    shiro16-colligate's file 
* @author   许立亢 
* @date     2015年9月16日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.github.star45.shiro.chapter16.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.star45.shiro.chapter16.dao.OrganizationDao;
import com.github.star45.shiro.chapter16.entity.Organization;
import com.github.star45.shiro.chapter16.service.OrganizationService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年9月16日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{
	 @Autowired
	    private OrganizationDao organizationDao;

	    @Override
	    public Organization createOrganization(Organization organization) {
	        return organizationDao.createOrganization(organization);
	    }

	    @Override
	    public Organization updateOrganization(Organization organization) {
	        return organizationDao.updateOrganization(organization);
	    }

	    @Override
	    public void deleteOrganization(Long organizationId) {
	        organizationDao.deleteOrganization(organizationId);
	    }

	    @Override
	    public Organization findOne(Long organizationId) {
	        return organizationDao.findOne(organizationId);
	    }

	    @Override
	    public List<Organization> findAll() {
	        return organizationDao.findAll();
	    }

	    @Override
	    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
	        return organizationDao.findAllWithExclude(excludeOraganization);
	    }

	    @Override
	    public void move(Organization source, Organization target) {
	        organizationDao.move(source, target);
	    }
}
