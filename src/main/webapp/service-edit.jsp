<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Service edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create service</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit service #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/service">
		<div class="row">
			<input type="hidden" name="id" required value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="type" required value="${dto.type}"  > <label for="type">Type</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="price" required value="${dto.price}"> <label for="price">Price</label>
				</div>
				
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/service"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>