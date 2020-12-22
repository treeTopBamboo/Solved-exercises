<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Result</title>
	<link rel="stylesheet" href="css/listpartstyle.css" />
</head>
<body>
	<h1>City Table List</h1>
	<!-- ↓検索機能の追加↓ -->
	<jsp:include page="parts/searchpart.jsp"></jsp:include>
	<!-- ↑検索機能の追加↑ -->
	<p class="right"><a href="IndexToListController?pos=1">一覧に戻る</a></p>
	<jsp:include page="parts/listpart.jsp"></jsp:include>
</body>
</html>
