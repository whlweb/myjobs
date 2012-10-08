package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import entity.FilmInfo;

public class QueryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String tid=request.getParameter("type");
		String actor = request.getParameter("actor");
		String director = request.getParameter("director");
		String price1 =request.getParameter("price1");
		String price2 = request.getParameter("price2");
		
		Dao dao = new Dao();
		List<FilmInfo> films=dao.query(name, tid, actor, director, price1, price2);
		request.setAttribute("films", films);

		request.getRequestDispatcher("query.jsp").forward(request,
				response);

	}
}
