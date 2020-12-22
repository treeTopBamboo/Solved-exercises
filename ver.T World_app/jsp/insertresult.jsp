
<!-- レコードの新規追加 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World DB City Table New Record</title>
	<link rel="stylesheet" href="css/editresultstyle.css" />
</head>
<body>
	<h2>新規レコードを追加しました</h2>
	<table>
		<tr><td class="line" colspan="5">登録した新規レコード</td></tr>
		<c:set var="citytblbean" value="${ newrow }" scope="session" />
		<jsp:include page="parts/resultpart.jsp"></jsp:include>
	</table>
	<p><a href="jsp/index.jsp">START</a></p>
</body>
</html>
