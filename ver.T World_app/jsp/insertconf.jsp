
<!-- レコードの新規追加 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World DB City Table New Record</title>
</head>
<body>
	<c:set var="citytblbean" value="${ newrow }" scope="session" />
	<h2>これでよろしいですか?</h2>
	<form action="InsertConfToUpdateController" method="POST">
		<jsp:include page="parts/confirmpart.jsp"></jsp:include>
		<input type="submit" value="追加" />
	</form>
	<p><a href="javascript:history.go(-1)">戻る</a></p>
</body>
</html>
