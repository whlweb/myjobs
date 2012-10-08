package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.UserServiceImpl;
import util.Page;
import dao.impl.UserDaoImpl;
import entity.User;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		if (request.getSession().getAttribute("role") == null) {
			out.println("<script language=\"javascript\">alert(\"您没有登陆或登陆已经超时！\");	window.parent.location=\"login.jsp?login=empty\";</script>");
			return;
		}
		UserServiceImpl usi = new UserServiceImpl();
		usi.setUd(new UserDaoImpl());
		String flag = request.getParameter("flag");
		// 响应查询操作
		if ("query".equals(flag)) {
			String q_name = request.getParameter("username");
			List<User> users = usi.query(q_name);
			if (users.size() == 0) {
				out.println("<script language=\"javascript\">alert(\"没有查询到相应的用户，请重试！\");	history.go(-1);</script>");
				out.flush();
			} else {
				request.setAttribute("users", users);
				request.getRequestDispatcher("user_query.jsp").forward(request,
						response);
			}
		} else if ("add".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int gender = Integer.parseInt(request.getParameter("gender"));
			int age = Integer.parseInt(request.getParameter("age"));
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String role = request.getParameter("role");

			// 封装成用户信息对象
			User user = new User();
			user.setAddress(address);
			user.setAge(age);
			user.setGender(gender);
			user.setId(id);
			user.setPassword(password);
			user.setPhone(phone);
			user.setRole(role);
			user.setUsername(username);
			boolean flag2 = usi.add(user);
			if (flag2) {
				request.getSession().setAttribute("users", usi.getUserList());
				out.println("<script language=\"javascript\">alert(\"添加成功！\");	window.location=\"user.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"添加失败！\");	history.go(-1);</script>");

			}
			out.flush();
		} else if ("edit".equals(flag)) {
			// 找到需要修改的用户对象
			String id = request.getParameter("row");
			List<User> users = (List<User>) request.getSession().getAttribute(
					"users");
			User u = null;
			for (User user : users) {
				if (user.getId() == Integer.parseInt(id)) {
					u = user;
					request.setAttribute("user", u);
					break;
				}
			}
			request.getRequestDispatcher("user_edit.jsp").forward(request,
					response);

		} else if ("revise".equals(flag)) {
			String id = request.getParameter("row");
			List<User> users = (List<User>) request.getSession().getAttribute(
					"users");
			User s = null;
			for (User user : users) {
				if (user.getId() == Integer.parseInt(id)) {
					s = user;
					request.setAttribute("user", s);
					break;
				}
			}
			request.getRequestDispatcher("user_revise.jsp").forward(request,
					response);
		} else if ("dorevise".equals(flag)) {
			// 修改订单对象
			String reviseid = request.getParameter("reviseid");

			int id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			int gender = Integer.parseInt(request.getParameter("gender"));
			int age = Integer.parseInt(request.getParameter("age"));
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String role = request.getParameter("role");

			// 封装成用户信息对象
			User user = new User();
			user.setAddress(address);
			user.setAge(age);
			user.setGender(gender);
			user.setId(id);
			user.setPhone(phone);
			user.setRole(role);
			user.setUsername(username);
			boolean flag2 = usi.update(user, reviseid);
			if (flag2) {
				request.getSession().setAttribute("users", usi.getUserList());
				out.println("<script language=\"javascript\">alert(\"修改成功！\");	window.location=\"user.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"修改失败！\");	history.go(-1);</script>");

			}

		} else if ("dorevise2".equals(flag)) {
			// 修改订单对象
			String reviseid = request.getParameter("reviseid");

			int id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			int gender = Integer.parseInt(request.getParameter("gender"));
			int age = Integer.parseInt(request.getParameter("age"));
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String role = "user";

			// 封装成用户信息对象
			User user = new User();
			user.setAddress(address);
			user.setAge(age);
			user.setGender(gender);
			user.setId(id);
			user.setPhone(phone);
			user.setRole(role);
			user.setUsername(username);

			boolean flag2 = usi.update(user, reviseid);
			if (flag2) {
				request.getSession().setAttribute("user", user);
				out.println("<script language=\"javascript\">alert(\"修改成功！\");	window.location=\"userAdmin2.jsp\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"修改失败！\");	history.go(-1);</script>");

			}

		} else if ("changepw".equals(flag)) {
			String id = request.getParameter("row");
			List<User> users = (List<User>) request.getSession().getAttribute(
					"users");
			User s = null;
			for (User user : users) {
				if (user.getId() == Integer.parseInt(id)) {
					s = user;
					request.setAttribute("user", s);
					break;
				}
			}
			request.getRequestDispatcher("user_changepassword.jsp").forward(
					request, response);
		} else if ("dochangepw".equals(flag)) {
			// 修改订单对象
			String reviseid = request.getParameter("reviseid");

			String oldpw = request.getParameter("oldpassword");
			System.out.println(oldpw);
			String newpw = request.getParameter("password");
			System.out.println(newpw);
			boolean flag2 = usi.changepw(reviseid, oldpw, newpw);
			if (flag2) {
				if (request.getSession().getAttribute("role").equals("admin")) {
					out.println("<script language=\"javascript\">alert(\"密码修改成功！\");	window.location=\"user.do?flag=list\";</script>");
				} else {
					out.println("<script language=\"javascript\">alert(\"密码修改成功！\");	window.location=\"userAdmin2.jsp\";</script>");

				}
			} else {
				out.println("<script language=\"javascript\">alert(\"密码修改失败！\");	window.location=\"user_changepassword.jsp?row="
						+ reviseid + "\";</script>");

			}

		} else if ("delete".equals(flag)) {
			// 删除操作
			String row = request.getParameter("row");

			boolean flag2 = usi.delete(row);
			if (flag2) {
				request.getSession().setAttribute("users", usi.getUserList());
				out.println("<script language=\"javascript\">alert(\"用户删除成功！\");	window.location=\"user.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"用户删除失败！\");	history.go(-1);</script>");

			}
			out.flush();
		} else if ("list".equals(flag)) {
			Page page = new Page();
			int currPage = Integer
					.parseInt(request.getParameter("page") == null ? "1"
							: request.getParameter("page"));
			int userPageSize = page.userPageSize;
			page.setPageSize(userPageSize);
			int userCount = usi.getTotalCount();
			page.setRecordCount(userCount);
			int userPageCount = page.getTotalPageCountByRs();

			List<User> users = usi.getByPage(currPage, userPageSize);
			request.setAttribute("users", users);
			request.setAttribute("currPage", currPage);
			request.setAttribute("userCount", userCount);
			request.setAttribute("userPageCount", userPageCount);
			request.getRequestDispatcher("userAdmin.jsp").forward(request,
					response);
		}
	}
}
