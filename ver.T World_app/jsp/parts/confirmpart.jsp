
<!-- レコードを編集する機能 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table border="1">
	<tr>
		<th>ID</th>
		<td><input type="hidden" name="id" value="${ citytblbean.id }">
		<c:out value="${ citytblbean.id }" /></td>
	</tr>
	<tr>
		<th>Name</th>
		<td><input type="text" name="name" value="<c:out value="${ citytblbean.name }" />" /></td>
	</tr>
	<tr>
		<th>CountryCode</th>
		<td><input type="text" name="countrycode" value="<c:out value="${ citytblbean.countrycode }" />" /></td>
	</tr>
	<tr>
		<th>District</th>
		<td><input type="text" name="district" value="<c:out value="${ citytblbean.district }" />" /></td>
	</tr>
	<tr>
		<th>Population<span><c:out value="${ err }" /></span></th>
		<td><input type="text" name="population" value="<c:out value="${ citytblbean.population }" />" /></td>
	</tr>
</table>
