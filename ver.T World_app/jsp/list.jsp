<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>World DB City Table List</title>
	<link rel="stylesheet" href="css/listpartstyle.css" />
</head>
<body>
	<h1>City Table List</h1>
	<!-- ↓検索機能の追加↓ -->
	<jsp:include page="parts/searchpart.jsp"></jsp:include>
	<br />
	<!-- ↑検索機能の追加↑ -->
	<jsp:include page="parts/listpart.jsp"></jsp:include>
	<p><a href="jsp/index.jsp">START</a></p>
</body>
</html>
