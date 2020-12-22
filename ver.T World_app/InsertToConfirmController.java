
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

@WebServlet("/InsertToConfirmController")
public class InsertToConfirmController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		CityTblBean newrow = new CityTblBean();
		String url = null;

		try
		{
			HttpSession session = request.getSession();
			newrow.setName(request.getParameter("name"));
			newrow.setCountrycode(request.getParameter("countrycode"));
			newrow.setDistrict(request.getParameter("district"));

			try
			{
				newrow.setPopulation(Long.parseLong(request.getParameter("population")));
				url = "/jsp/insertconf.jsp";

				session.setAttribute("newrow", newrow);

				ServletContext context = getServletContext();
				RequestDispatcher rd = context.getRequestDispatcher(url);
				rd.forward(request, response);
			}
			catch (Exception ex)
			{
				newrow.setPopulation(0);
				session.setAttribute("newrow", newrow);
				session.setAttribute("err", "(*)");
				url = "jsp/insert.jsp";
				response.sendRedirect(url);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

//*/
