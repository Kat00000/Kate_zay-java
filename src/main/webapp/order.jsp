<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="Orders list" scope="application" />
<c:set var="pageUrl" value="/order" scope="page" />
<t:wrapper>
<h1>Orders list</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light" href="/order?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">DB ID</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="timeStay">Time stay</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="user">User</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="room">Room</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="service">Service</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="updated">Update date</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="created">Creation date</mytaglib:sort-link></th>
				<th>actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.timeStay}" /></td>
					<td><c:out value="${entity.userName}" /></td>
					<td><c:out value="${entity.roomName}" /></td>
					<td><c:out value="${entity.serviceName}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.updated}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/order?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/order?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<t:paging />
</t:wrapper>