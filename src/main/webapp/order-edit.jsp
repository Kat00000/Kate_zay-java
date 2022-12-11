<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Holel edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create order</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit hotel #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/order">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="number" required name="timeStay" required  maxlength=2 value="${dto.timeStay}"  > <label for="timeStay">Time stay</label>
				</div>
			</div>
			<div class="row">
				
				<div class="col s6">
					<label for="modelId">User ID</label> 
					<select name="userId" class="browser-default" required>
						<option value="">--select users--</option>
						<c:forEach items="${allUsers}" var="user">
							<option value="${user.id}" <c:if test="${user.id eq dto.userId}">selected="selected"</c:if>>${user.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s6">
					<label for="roomId">Room ID</label> 
					<select name="roomId" class="browser-default" required>
						<option value="">--select rooms--</option>
						<c:forEach items="${allRooms}" var="room">
							<option value="${room.id}" <c:if test="${room.id eq dto.roomId}">selected="selected"</c:if>>${room.number}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s6">
					<label for="serviceId">Service ID</label> 
					<select name="serviceId" class="browser-default" required>
						<option value="">--select service--</option>
						<c:forEach items="${allServices}" var="service">
							<option value="${service.id}" <c:if test="${service.id eq dto.serviceId}">selected="selected"</c:if>>${service.type}</option>
						</c:forEach>
					</select>
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
    