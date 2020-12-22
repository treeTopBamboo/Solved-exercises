<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ↓検索機能の追加↓ -->
<div class="right">
	<form action="ListToResultController" method="POST">
		<h3>カントリーコードで検索</h3>
		<input type="text" name="ccode" />
		<input type="submit" value="検索" />
	</form>
</div>
<!-- ↑検索機能の追加↑ -->