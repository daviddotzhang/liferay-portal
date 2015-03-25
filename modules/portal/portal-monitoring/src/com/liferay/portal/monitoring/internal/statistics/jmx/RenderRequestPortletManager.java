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

package com.liferay.portal.monitoring.internal.statistics.jmx;

import com.liferay.portal.monitoring.internal.statistics.portlet.RenderRequestSummaryStatistics;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {
		"object-name=com.liferay.portal.monitoring:classification=portlet_statistic,name=RenderRequestPortletManager",
		"object-name-cache-key=RenderRequestPortletManager"
	},
	service = DynamicMBean.class
)
public class RenderRequestPortletManager extends PortletManager {

	public RenderRequestPortletManager() throws NotCompliantMBeanException {
		super(PortletManagerMBean.class);
	}

	@Reference
	protected void setRenderRequestSummaryStatistics(
		RenderRequestSummaryStatistics renderRequestSummaryStatistics) {

		super.setPortletSummaryStatistics(renderRequestSummaryStatistics);
	}

}