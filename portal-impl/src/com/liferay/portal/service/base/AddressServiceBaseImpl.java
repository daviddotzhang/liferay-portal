/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Address;
import com.liferay.portal.service.AddressService;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.AddressPersistence;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.CountryPersistence;
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the address remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.AddressServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.AddressServiceImpl
 * @see com.liferay.portal.service.AddressServiceUtil
 * @generated
 */
public abstract class AddressServiceBaseImpl extends BaseServiceImpl
	implements AddressService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.AddressServiceUtil} to access the address remote service.
	 */

	/**
	 * Returns the address local service.
	 *
	 * @return the address local service
	 */
	public com.liferay.portal.service.AddressLocalService getAddressLocalService() {
		return addressLocalService;
	}

	/**
	 * Sets the address local service.
	 *
	 * @param addressLocalService the address local service
	 */
	public void setAddressLocalService(
		com.liferay.portal.service.AddressLocalService addressLocalService) {
		this.addressLocalService = addressLocalService;
	}

	/**
	 * Returns the address remote service.
	 *
	 * @return the address remote service
	 */
	public com.liferay.portal.service.AddressService getAddressService() {
		return addressService;
	}

	/**
	 * Sets the address remote service.
	 *
	 * @param addressService the address remote service
	 */
	public void setAddressService(
		com.liferay.portal.service.AddressService addressService) {
		this.addressService = addressService;
	}

	/**
	 * Returns the address persistence.
	 *
	 * @return the address persistence
	 */
	public AddressPersistence getAddressPersistence() {
		return addressPersistence;
	}

	/**
	 * Sets the address persistence.
	 *
	 * @param addressPersistence the address persistence
	 */
	public void setAddressPersistence(AddressPersistence addressPersistence) {
		this.addressPersistence = addressPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the country remote service.
	 *
	 * @return the country remote service
	 */
	public com.liferay.portal.service.CountryService getCountryService() {
		return countryService;
	}

	/**
	 * Sets the country remote service.
	 *
	 * @param countryService the country remote service
	 */
	public void setCountryService(
		com.liferay.portal.service.CountryService countryService) {
		this.countryService = countryService;
	}

	/**
	 * Returns the country persistence.
	 *
	 * @return the country persistence
	 */
	public CountryPersistence getCountryPersistence() {
		return countryPersistence;
	}

	/**
	 * Sets the country persistence.
	 *
	 * @param countryPersistence the country persistence
	 */
	public void setCountryPersistence(CountryPersistence countryPersistence) {
		this.countryPersistence = countryPersistence;
	}

	/**
	 * Returns the list type remote service.
	 *
	 * @return the list type remote service
	 */
	public com.liferay.portal.service.ListTypeService getListTypeService() {
		return listTypeService;
	}

	/**
	 * Sets the list type remote service.
	 *
	 * @param listTypeService the list type remote service
	 */
	public void setListTypeService(
		com.liferay.portal.service.ListTypeService listTypeService) {
		this.listTypeService = listTypeService;
	}

	/**
	 * Returns the list type persistence.
	 *
	 * @return the list type persistence
	 */
	public ListTypePersistence getListTypePersistence() {
		return listTypePersistence;
	}

	/**
	 * Sets the list type persistence.
	 *
	 * @param listTypePersistence the list type persistence
	 */
	public void setListTypePersistence(ListTypePersistence listTypePersistence) {
		this.listTypePersistence = listTypePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return Address.class;
	}

	protected String getModelClassName() {
		return Address.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = addressPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portal.service.AddressLocalService.class)
	protected com.liferay.portal.service.AddressLocalService addressLocalService;
	@BeanReference(type = com.liferay.portal.service.AddressService.class)
	protected com.liferay.portal.service.AddressService addressService;
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.CountryService.class)
	protected com.liferay.portal.service.CountryService countryService;
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = com.liferay.portal.service.ListTypeService.class)
	protected com.liferay.portal.service.ListTypeService listTypeService;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	private String _beanIdentifier;
}