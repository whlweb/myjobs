package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.BillDaoImpl;
import dao.impl.SupplierDaoImpl;
import dao.impl.UserDaoImpl;

import service.impl.BillServiceImpl;
import service.impl.SupplierServiceImpl;
import service.impl.UserServiceImpl;
import entity.Bill;
import entity.Supplier;
import entity.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		if (flag == null || flag == "") {
			response.sendRedirect("login.jsp?login=empty");
		}
		if (flag.equals("login")) {
			UserServiceImpl usi = new UserServiceImpl();
			usi.setUd(new UserDaoImpl());
			BillServiceImpl bsi = new BillServiceImpl();
			bsi.setBilldao(new BillDaoImpl());
			SupplierServiceImpl ssi = new SupplierServiceImpl();
			ssi.setSd(new SupplierDaoImpl());
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (session.getAttribute("user") == null) {
				if (username == null || password == null) {
					response.sendRedirect("login.jsp?login=empty");
				} else {
					User user = usi.login(username, password);
					if (user == null) {
						response.sendRedirect("login.jsp?login=error");
					} else {
						List<Bill> bills=bsi.getBillList();
						List<Supplier> suppliers = ssi.getSupplierList();
						List<User> users = usi.getUserList();
						session.setAttribute("bills", bills);
						session.setAttribute("suppliers", suppliers);
						session.setAttribute("users", users);

						session.setAttribute("user", user);
						session.setAttribute("role", user.getRole());
						response.sendRedirect("admin_index.jsp");
					}
				}
			}

		} else if (flag.equals("exit")) {
			session.invalidate();
			response.sendRedirect("login.jsp");

		}

	}
}
