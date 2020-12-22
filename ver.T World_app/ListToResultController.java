
package worldapp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListToResultController")
public class ListToResultController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		try
		{
			// ↓検索機能の追加↓
			String ccode = request.getParameter("ccode");
			HttpSession session = request.getSession();
			ArrayList<CityTblBean> citylist = CityTblBean.selectCcodeList(ccode);

			session.setAttribute("citylist", citylist);
			session.setAttribute("filename", "ListToResultController");
			// ↑検索機能の追加↑
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/searchresult.jsp");
		rd.forward(request, response);
	}
}
