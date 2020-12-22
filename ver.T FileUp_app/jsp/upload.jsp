<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップローダー</title>
</head>
<body>
	<form action="../UploadController" method="POST" enctype="multipart/form-data" >
		<h1>アップロードするファイル</h1>
		<input type="file" name="file" size="75" /><br />
		<input type="submit" value="アップロード" />
	</form>
	<p><c:choose>
		<c:when test="${ not empty filelist.filelist }">
			<a href="filelist.jsp">一覧表示</a>
		</c:when>
	</c:choose></p>
</body>
</html>