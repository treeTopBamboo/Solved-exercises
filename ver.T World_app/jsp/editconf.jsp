
<!-- レコードを編集する機能 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>World DB City Table Edit</title>
</head>
<body>
	<c:set var="citytblbean" value="${ editrow }" scope="session" />
	<h2>これでよろしいですか?</h2>
	<form action="EditConfToUpdateController" method="POST">
		<jsp:include page="parts/confirmpart.jsp"></jsp:include>
		<input type="submit" value="更新" />
	</form>
	<p><a href="javascript:history.go(-1)">戻る</a></p>
</body>
</html>
