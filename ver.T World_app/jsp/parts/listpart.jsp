<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="center">
	<tr>
		<th>ID</th>
		<th class="name">Name</th>
		<th>CountryCode</th>
		<th class="district">District</th>
		<th>Population</th>
		<!-- ↓cityテーブルのレコードを編集する機能を追加↓ -->
		<!-- ↓cityテーブルのレコードを削除する機能を追加↓ -->
		<th>Operation</th>
		<!-- ↑cityテーブルのレコードを編集する機能を追加↑ -->
		<!-- ↑cityテーブルのレコードを削除する機能を追加↑ -->
	</tr>
	<c:forEach var="citylistitem" items="${ citylist }">
	<tr>
		<td class="right"><c:out value="${ citylistitem.id }" /></td>
		<td class="left tmargin"><c:out value="${ citylistitem.name }" /></td>
		<td class="center"><c:out value="${ citylistitem.countrycode }" /></td>
		<td class="left tmargin"><c:out value="${ citylistitem.district }" /></td>
		<td class="right"><fmt:formatNumber value="${ citylistitem.population }" type="NUMBER" /></td>
		<!-- ↓cityテーブルのレコードを編集する機能を追加↓ -->
		<td class="center"><a href="ListToEditController?id=${ citylistitem.id }&op=e">Edit</a>,
		<!-- ↑cityテーブルのレコードを編集する機能を追加↑ -->
		<!-- ↓cityテーブルのレコードを削除する機能を追加↓ -->
			<a href="ListToEditController?id=${ citylistitem.id }&op=d">Delete</a></td>
		<!-- ↑cityテーブルのレコードを削除する機能を追加↑ -->
	</tr>
	</c:forEach>
	<c:choose>
		<c:when test="${ filename == 'IndexToListController' }">
			<tr>
				<td class="left"><p><a href="IndexToListController?pos=${ pb.previous }">＜＜</a></p></td>

				<td colspan="3"></td>
				<td class="right"><p><a href="IndexToListController?pos=${ pb.next }">＞＞</a></p></td>
			</tr>
		</c:when>
	</c:choose>
</table>
