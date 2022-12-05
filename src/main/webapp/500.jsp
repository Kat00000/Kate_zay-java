<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="500 Internal Server Error." scope="application" />
<t:wrapper>
	<h1>Ой, сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос.</h1>
	<h1>Попробуйте начать с <a  href="/">домашней страницы</a></h1>
</t:wrapper>