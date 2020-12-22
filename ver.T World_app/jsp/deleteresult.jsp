

<!-- レコードを削除する処理 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World DB City Table Deleted</title>
<link rel="stylesheet" href="css/editresultstyle.css" />
</head>
<body>
	<h2>レコードを削除しました</h2>
	<table>
		<tr><td class="line" colspan="5">削除データ</td></tr>
		<c:set var="citytblbean" value="${ orgrow }" scope="session" />
		<jsp:include page="parts/resultpart.jsp"></jsp:include>
	</table>
	<p><a href="jsp/index.jsp">START</a></p>
</body>
</html>
