package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class XmlServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public XmlServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		String username = request.getParameter("user");
		PrintWriter out = response.getWriter();
		StringBuilder sb=new StringBuilder();
		sb.append("<userinfo>");
		sb.append("<username id=\"username\">");
		if(username==null||username.trim().equals("")){
			sb.append("Please select a user!");
		}else{
			sb.append(username);
		}
		sb.append("</username>");
		sb.append("</userinfo>");

		out.print(sb.toString());
		out.flush();
		out.close();
	}

}
