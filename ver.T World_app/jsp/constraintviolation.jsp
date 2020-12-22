
<!-- レコードの新規追加 -->

<!--
	【constraintviolation.jsp】

	このファイルはテキストにはありません。

	テキストに記載のコードは、
	CountryCodeの制約違反をスルーしていたので
	ID=0 で登録できたように見える挙動となっていました。

	登録処理を行わずに、このページに飛ぶように
	処理を変更しています。


	// 【参考】InsertConfToUpdateController.java

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		Integer result = null;
		HttpSession session = request.getSession();
		CityTblBean newrow = (CityTblBean)session.getAttribute("newrow");

		/*
		 *  ここからCountryCodeの制約違反に対する例外処理を
		 *  オリジナルで実装しています。
		 *
		 *  制約違反の場合は CityTblBean.getNewRowId()が 0 を返してくるので
		 *  それを見て判定しています。
		 *
		 *	制約違反の場合は、CityTblBean.insertNewRow(newrow) の結果も無効です。
		 *	レコードが作成されません。
		 */

		// 例外処理
		int id = 1;
		try
		{
			// レコード挿入処理
			result = CityTblBean.insertNewRow(newrow);

			// 挿入後のIDを取得する
			id = CityTblBean.getNewRowId(newrow.getName(),newrow.getCountrycode());
		}
		catch( Exception ex )
		{
			// 特になし
			ex.printStackTrace();
		}

		// 取得したIDで制約違反を判定する
		if ( id == 0 )
		{
			// 制約違反なので それを示すページへ移動して関数の処理を終了
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/jsp/constraintviolation.jsp");
			rd.forward(request, response);

			return;
		}
		else
		{
			// IDをセットする
			newrow.setId(id);

			request.setAttribute("result", result);
			request.setAttribute("newrow", newrow);
			session.removeAttribute("newrow");

			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/jsp/insertresult.jsp");
			rd.forward(request, response);
		}
	}


-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>制約違反</title>
</head>
<body>
	<c:set var="citytblbean" value="${ newrow }" scope="session" />
	<h2>CountryCode の制約違反です。</h2>
	<p>countryテーブルに登録された CountryCode を使用してください。</p>
	<p><a href="jsp/insert.jsp">登録画面へ</a></p>
</body>
</html>
