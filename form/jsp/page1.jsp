<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String[] seminar = {"Strutsセミナー","JSPセミナー","Javaセミナー"};
	pageContext.setAttribute("seminar", seminar);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page1.jsp</title>

</head>
<body>
	<form action="../Page1to2Controller" method="POST">
	受講したいセミナーと受講人数<br/>
		<table border="1">
			<tr>
				<th>セミナー名</th>
				<th>人数</th>
			</tr>
			<c:forEach var="seminaritem" items="${ seminar }">
			<tr>
				<td>
					<input type="hidden" name="seminar" value="${ seminaritem }"/>
					<c:out value="${ seminaritem }"/>
				</td>
				<td>
					<select name="ninzu">
						<c:forEach var="i" begin="0" end="100" step="1">
							<option value="${ i }">
								<c:out value="${ i }"/>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</c:forEach>

		</table>
		<p><input type="submit" value="申し込む" /></p>
	</form>
</body>
</html>