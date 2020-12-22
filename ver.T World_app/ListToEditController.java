
// レコードを編集する機能
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

@WebServlet("/ListToEditController")
public class ListToEditController extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		CityTblBean orgrow = new CityTblBean();
		String url = "/jsp/edit.jsp";

		try
		{
			int id = Integer.parseInt(request.getParameter("id"));
			orgrow = CityTblBean.selectEditRow(id);
			session.setAttribute("orgrow", orgrow);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		// ↓レコードを削除する↓
		if ( request.getParameter("op").equals("e") )
		{
			url = "/jsp/edit.jsp";
		}
		else if ( request.getParameter("op").contentEquals("d") )
		{
			url = "/jsp/deleteconf.jsp";
		}
		// ↑レコードを削除する↑

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		CityTblBean editrow = new CityTblBean();
		String url = null;

		try
		{
			editrow.setId(Integer.parseInt(request.getParameter("id")));
			editrow.setName(request.getParameter("name"));
			editrow.setCountrycode(request.getParameter("countrycode"));
			editrow.setDistrict(request.getParameter("district"));

			try
			{
				editrow.setPopulation(Long.parseLong(request.getParameter("population")));
				url = "/jsp/editconf.jsp";
			}
			catch (Exception ex)
			{
				CityTblBean orgrow = (CityTblBean) session.getAttribute("orgrow");
				editrow.setPopulation(orgrow.getPopulation());
				request.setAttribute("err", "(*)");
				url = "/jsp/edit.jsp";
			}

			session.setAttribute("editrow", editrow);
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

