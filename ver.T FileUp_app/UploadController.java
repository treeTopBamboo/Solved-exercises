package fileupload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadController
 */
@MultipartConfig(location = "c:/Servlet_JSP/temp/")
@WebServlet("/UploadController")
public class UploadController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadController()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");

		try
		{
			System.out.println("Error : 0");
			Part part = request.getPart("file");
			System.out.println("Error : 1");
			String submitname = getFilename(part);
			System.out.println("Error : 2");
			StringBuilder sb = new StringBuilder(submitname.replace("\\", "/"));
			System.out.println("Error : 3");
			String name = sb.substring(sb.lastIndexOf("/") + 1).toString();
			System.out.println("Error : 4");

			ServletContext context = getServletContext();

			if (isValidType(name))
			{
				part.write(context.getRealPath("/img") + "/" + name);

				File fl = new File(context.getRealPath("/img/"));
				FileListBean flb = (FileListBean) context.getAttribute("filelist");
				flb.setFilelist(fl.list());
				context.setAttribute("filelist", flb);

				//				response.getWriter().println(context.getRealPath("/img") + "\\" + name);
				response.sendRedirect("jsp/filelist.jsp");
			}
			else
			{
				//				response.getWriter().print("ファイル選択していないか対応拡張子ではありませんよ");
				response.sendRedirect("jsp/fail.jsp?mes=1");
			}
		}
		catch (Exception ex)
		{
			//			ex.printStackTrace();
			System.out.println("Error : Exception");
			response.sendRedirect("jsp/fail.jsp?mes=2");
		}
	}

	// ファイル名抽出
	private String getFilename(Part part)
	{
		for (String cd : part.getHeader("Content-Disposition").split(";"))
		{
			if (cd.trim().startsWith("filename"))
			{
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}

		return null;
	}

	// 拡張子のチェック
	private boolean isValidType(String name)
	{
		if (name.length() != 0)
		{
			String[] paras = { "gif", "jpg", "jpeg", "png", "bmp" };
			String[] names = name.split("\\.");

			for (String para : paras)
			{
				if (para.equals(names[names.length - 1]) || para.equals(names[names.length - 1].toLowerCase()))
				{
					return true;
				}
			}
		}

		return false;
	}

}
