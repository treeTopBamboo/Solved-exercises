
<!-- レコードを編集する機能 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tr>
	<th>ID</th>
	<th>Name</th>
	<th>CountryCode</th>
	<th>District</th>
	<th>Population</th>
</tr>

<tr>
	<td><c:out value="${ citytblbean.id }" /></td>
	<td><c:out value="${ citytblbean.name }" /></td>
	<td><c:out value="${ citytblbean.countrycode }" /></td>
	<td><c:out value="${ citytblbean.district }" /></td>
	<td><c:out value="${ citytblbean.population }" /></td>
</tr>
