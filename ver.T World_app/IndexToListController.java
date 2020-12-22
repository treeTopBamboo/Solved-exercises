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

/**
* Servlet implementation class IndexToListController
*/
@WebServlet("/IndexToListController")
public class IndexToListController extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		try
		{
			int pos = Integer.parseInt(request.getParameter("pos"));
			HttpSession session = request.getSession();
			ArrayList<CityTblBean> citylist = CityTblBean.selectAllList(pos);

			session.setAttribute("citylist", citylist);
			PagingBean pb = new PagingBean();

			if(citylist.size() < PagingBean.PAGESIZE)
			{
				pb.setNext(pos);
			}
			else
			{
				pb.setNext(pos + PagingBean.PAGESIZE);
			}

			if((pos-PagingBean.PAGESIZE) <= 0)
			{
				pb.setPrevious(pos);
			}
			else
			{
				pb.setPrevious(pos - PagingBean.PAGESIZE);
			}

			session.setAttribute("pb", pb);
			session.setAttribute("filename", "IndexToListController");

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/list.jsp");
		rd.forward(request, response);
	}


	/**
	* @see HttpServlet#doPost(HttpServletRequest request,
	HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
