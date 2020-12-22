
<!-- レコードを編集する機能 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World DB City Table Edit</title>
<style type="text/css">
	<!--
		span{color: red}
	-->
</style>
</head>
<body>
	<c:choose>
		<c:when test="${ empty err }">
			<c:set var="citytblbean" value="${ orgrow }" scope="session" />
		</c:when>
		<c:otherwise>
			<c:set var="citytblbean" value="${ editrow }" scope="session" />
		</c:otherwise>
	</c:choose>
	<h2>レコード更新</h2>
	<form action="ListToEditController" method="POST">
		<jsp:include page="parts/confirmpart.jsp"></jsp:include>
		<input type="submit" value="更新" />
	</form>
	<p><a href="javascript:history.go(-1)">戻る</a></p>
</body>
</html>
