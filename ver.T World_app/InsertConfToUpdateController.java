
// レコードの新規追加

package worldapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InsertConfToUpdateController")
public class InsertConfToUpdateController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

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
}