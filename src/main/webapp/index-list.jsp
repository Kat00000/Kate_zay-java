<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Hotel list" scope="application" />
<t:wrapper>

<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Make an order</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit order #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/order">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="timeStay" value="${dto.timeStay}" ${empty dto.id ? '' : 'disabled'} > <label for="timeStay">Time stay</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="userId" value="${dto.userId}"> <label for="userId">User ID</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="roomId" value="${dto.roomId}"> <label for="roomId">Room ID</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="serviceId" value="${dto.serviceId}"> <label for="serviceId">Service ID</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/order"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>

</t:wrapper>