<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルリスト</title>
</head>
<body>
	<h1>ファイルリスト</h1>
	<table border="1">
		<c:forEach var="flist" items="${ filelist.filelist }">
			<tr><td><img src="../img/${ flist }" alt="${ flist }" /></td></tr>
		</c:forEach>
	</table>
	<p><a href="upload.jsp">ファイルの追加画面に戻る</a></p>
</body>
</html>