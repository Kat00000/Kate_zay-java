<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title><c:out value="${pageTitle}" /></title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src="js/helpers.js"></script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		M.FormSelect.init(document.querySelectorAll('select'), {});
	});
</script>
</head>
<nav class="#c51162 pink accent-4" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="#" class="brand-logo">Logo</a>
		<ul class="right hide-on-med-and-down">
		    <li><a href="order"> Items list</a></li>
		    <li><a href="user"> Users</a></li>
			<li><a href="room"> Hotel rooms</a></li>
			<li><a href="service"> Services</a></li>
			
		</ul>
	</div>
</nav>
<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<jsp:doBody />
		<!-- Page body will be here -->
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


</html>