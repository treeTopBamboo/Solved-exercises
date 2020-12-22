
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

@WebServlet("/EditConfToUpdateController")
public class EditConfToUpdateController extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		CityTblBean editrow = (CityTblBean)session.getAttribute("editrow");
		Integer result = null;

		try
		{
			result = CityTblBean.updateEditRow(editrow);
			request.setAttribute("result", result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/editresult.jsp");
		rd.forward(request, response);
	}
}