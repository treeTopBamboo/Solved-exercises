
<!-- レコードを削除する処理 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World DB City Table Delete</title>
</head>
<body>
	<c:set var="citytblbean" value="${ orgrow }" scope="session" />
	<h2>以下のレコードを削除します。よろしいですか?</h2>
	<form action="DeleteConfToUpdateController" method="POST">
		<jsp:include page="parts/confirmpart.jsp"></jsp:include>
		<input type="submit" value="削除" />
	</form>
	<p><a href="javascript:history.go(-1)">戻る</a></p>
</body>
</html>
