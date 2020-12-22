
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

/**
 * Servlet implementation class DeleteConfTOUpdateController
 */
@WebServlet("/DeleteConfToUpdateController")
public class DeleteConfToUpdateController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfToUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		CityTblBean delrow = (CityTblBean)session.getAttribute("orgrow");
		Integer result = null;

		try
		{
			result = CityTblBean.deleteRow(delrow.getId());
			request.setAttribute("result", result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/deleteresult.jsp");
		rd.forward(request, response);
	}

}

/*
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

@WebServlet("/DeleteConfToUpdateController")
public class DeleteConfToUpdateController extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		CityTblBean delrow = (CityTblBean)session.getAttribute("orgrow");
		Integer result = null;

		try
		{
			result = CityTblBean.deleteRow(delrow.getId());
			request.setAttribute("result", result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/deleteresult.jsp");
		rd.forward(request, response);
	}
}
//*/