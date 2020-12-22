<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイル選択・種類の確認</title>
</head>
<body>
	<c:choose>
		<c:when test="${ param.mes==1 }">
			<p>ファイルを選択していないか、拡張子が対応していません</p>
		</c:when>
		<c:otherwise>
			<p>その他のエラーが発生</p>
		</c:otherwise>
	</c:choose>
	<p><a href="upload.jsp">戻る</a></p>
</body>
</html>