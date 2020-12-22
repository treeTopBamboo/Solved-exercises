<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<form action="../LoginController" method="POST">
		<table border="1">
			<tr>
				<th>ログイン名</th>
				<td><input type="text" name="loginName" size="50" /></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="loginPass" size="50" /></td>
			</tr>
		</table>
		<input type="submit" value="ログイン" />
	</form>
</body>
</html>
