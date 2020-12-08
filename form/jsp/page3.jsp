<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page3.jsp</title>
</head>
<body>

	申し込みを確定しました。<br/>

		<!-- page2とpage3で共通する部分を省略したい場合 -->
		<!-- 別途ファイル(例:parts.jsp)を作成して共通部分を記述 -->
		<!-- 該当箇所に<jsp:include page="parts/parts.jsp"></jsp:include>と記述すれば省略可能 -->
		<table border="1">
			<tr>
				<th>セミナー名</th>
				<th>人数</th>
			</tr>
			<c:forEach var="orderlistitem" items="${ orderlist }">
			<tr>
				<td><c:out value="${ orderlistitem.seminar }"/></td>
				<td><c:out value="${ orderlistitem.ninzu }"/></td>
			</tr>
			</c:forEach>
		</table>
		<!-- page2とpage3で共通する部分 -->

</body>
</html>