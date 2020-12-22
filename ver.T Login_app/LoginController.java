package loginapp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		LoginBean loginbean = null;
		String url = null;

		try
		{
			/* ----------------------------------------------------
			  doPost関数の try の中身を全て以下と入れ替えます。
			  リストの取得とユーザ・パスワードの照合を、
			  取得したレコードすべてで行います。
			---------------------------------------------------- */

			//*
			// 複数ユーザ対応

			// フォームの情報を取得する
			String loginName = request.getParameter("loginName");
			String loginPass = request.getParameter("loginPass");

			// LoginBeanのリストを取得する
			ArrayList<LoginBean> loginAllList = null;
			loginAllList = LoginBean.loginAllData();

			// ユーザ・パスワード一致のフラグを定義。trueで一致
			boolean flag = false;

			// ユーザ・パスワードの一致を調べる
			for ( int i = 0; i < loginAllList.size(); i++ )
			{
				// リストからLoginBeanを取得
				loginbean = (LoginBean)(loginAllList.get(i));

				// レコード毎のユーザ・パスワードを取得する
				String lb_name = loginbean.getLoginName();
				String lb_pass = loginbean.getLoginPass();

				// ユーザとパスワードの一致を確認
				if (loginName.equals(lb_name) && loginPass.equals(lb_pass))
				{
					// 一致した場合の処理
					url = "/jsp/success.jsp";
					request.setAttribute("loginName", lb_name );

					// フラグを立てて探索終了
					flag = true;
					break;
				}
			}

			// フラグが立っていなければ、失敗を通知するページへ。
			if ( !flag )
			{
				url = "/jsp/fail.jsp";
			}
			//*/


			/*
			// 練習問題04
			String loginName = request.getParameter("loginName");
			String loginPass = request.getParameter("loginPass");

			if (loginName.equals("") || loginPass.equals(""))
			{
				url = "/jsp/fail.jsp";
			}
			else
			{
				loginbean = LoginBean.login();

				if (loginbean.getLoginName().equals(loginName) && loginbean.getLoginPass().equals(loginPass))
				{
					url = "/jsp/success.jsp";
					request.setAttribute("loginName", loginName);
				}
				else
				{
					url = "/jsp/fail.jsp";
				}
			}
			//*/
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
