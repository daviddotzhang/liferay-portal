<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

String orderByCol = ParamUtil.getString(request, "orderByCol", "create-date");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

int layoutSetPrototypesCount = LayoutSetPrototypeLocalServiceUtil.searchCount(company.getCompanyId(), null);

PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:error exception="<%= RequiredLayoutSetPrototypeException.class %>" message="you-cannot-delete-site-templates-that-are-used-by-a-site" />

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item cssClass="active" label="templates" />
	</aui:nav>
</aui:nav-bar>

<c:if test='<%= (layoutSetPrototypesCount > 0) || !navigation.equals("all") %>'>
	<liferay-frontend:management-bar
		checkBoxContainerId="layoutSetPrototypeSearchContainer"
		includeCheckBox="<%= true %>"
	>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all"} %>'
				portletURL="<%= renderResponse.createRenderURL() %>"
			/>

			<liferay-frontend:management-bar-sort
				orderByCol="<%= orderByCol %>"
				orderByType="<%= orderByType %>"
				orderColumns='<%= new String[] {"create-date"} %>'
				portletURL="<%= renderResponse.createRenderURL() %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= portletURL %>"
				selectedDisplayStyle="<%= displayStyle %>"
			/>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button href="javascript:;" iconCssClass="icon-trash" id="deleteSelectedLayoutSetPrototypes" />
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>
</c:if>

<aui:form cssClass="container-fluid-1280" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-site-templates.-you-can-add-a-site-template-by-clicking-the-plus-button-on-the-bottom-right-corner"
		headerNames="name"
		id="layoutSetPrototype"
		iteratorURL="<%= portletURL %>"
		rowChecker="<%= new EmptyOnClickRowChecker(renderResponse) %>"
		total="<%= layoutSetPrototypesCount %>"
	>
		<aui:input name="layoutSetPrototypeIds" type="hidden" />

		<%
		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<LayoutSetPrototype> orderByComparator = new LayoutSetPrototypeCreateDateComparator(orderByAsc);

		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(orderByType);
		%>

		<liferay-ui:search-container-results
			results="<%= LayoutSetPrototypeLocalServiceUtil.search(company.getCompanyId(), null, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.LayoutSetPrototype"
			cssClass="selectable"
			escapedModel="<%= true %>"
			keyProperty="layoutSetPrototypeId"
			modelVar="layoutSetPrototype"
		>

			<%
			String rowURL = null;

			Group group = layoutSetPrototype.getGroup();

			if (LayoutSetPrototypePermissionUtil.contains(permissionChecker, layoutSetPrototype.getLayoutSetPrototypeId(), ActionKeys.UPDATE) && (group.getPrivateLayoutsPageCount() > 0)) {
				rowURL = group.getDisplayURL(themeDisplay, true);
			}
			%>

			<liferay-ui:search-container-column-text
				name="name"
			>

				<aui:a href="<%= rowURL %>" target="_blank"><%= layoutSetPrototype.getName(locale) %></aui:a>

				<%
				int mergeFailCount = SitesUtil.getMergeFailCount(layoutSetPrototype);
				%>

				<c:if test="<%= mergeFailCount > PropsValues.LAYOUT_SET_PROTOTYPE_MERGE_FAIL_THRESHOLD %>">
					<liferay-ui:icon
						iconCssClass="icon-warning-sign"
						message='<%= LanguageUtil.format(request, "the-propagation-of-changes-from-the-x-has-been-disabled-temporarily-after-x-errors", new Object[] {mergeFailCount, LanguageUtil.get(request, "site-template")}, false) %>'
					/>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="description"
				value="<%= layoutSetPrototype.getDescription(locale) %>"
			/>

			<liferay-ui:search-container-column-date
				name="create-date"
				property="createDate"
			/>

			<liferay-ui:search-container-column-text
				cssClass="list-group-item-field"
				name="active"
			>
				<%= LanguageUtil.get(request, layoutSetPrototype.isActive()? "yes" : "no") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				cssClass="list-group-item-field"
				href="<%= rowURL %>"
				path="/layout_set_prototype_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_LAYOUT_SET_PROTOTYPE) %>">
	<portlet:renderURL var="addLayoutSetPrototypeURL">
		<portlet:param name="mvcPath" value="/edit_layout_set_prototype.jsp" />
	</portlet:renderURL>

	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "add") %>' url="<%= addLayoutSetPrototypeURL.toString() %>" />
	</liferay-frontend:add-menu>
</c:if>

<aui:script sandbox="<%= true %>">
	var Util = Liferay.Util;

	var form = $(document.<portlet:namespace />fm);

	$('#<portlet:namespace />deleteSelectedLayoutSetPrototypes').on(
		'click',
		function() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
				<portlet:actionURL name="deleteLayoutSetPrototypes" var="deleteURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:actionURL>

				form.fm('layoutSetPrototypeIds').val(Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form, '<%= deleteURL %>');
			}
		}
	);
</aui:script>