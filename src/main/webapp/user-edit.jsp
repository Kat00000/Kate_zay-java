<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="User edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create user</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit user #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/user">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="name" required value="${dto.name}" > <label for="name">Name</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="email" name="email" required value="${dto.email}"> <label for="email">Email</label>
				</div>
				<div class="input-field col s6">
					<input type="password" name="password" required minlength=4 maxlength=15 value="${dto.password}"> <label for="password">Password</label>
				</div>
				
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/user"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>