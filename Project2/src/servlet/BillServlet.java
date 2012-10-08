package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.impl.BillServiceImpl;
import util.Page;
import dao.impl.BillDaoImpl;
import entity.Bill;

@SuppressWarnings("serial")
public class BillServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BillServlet() {
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
		BillServiceImpl bsi = new BillServiceImpl();
		bsi.setBilldao(new BillDaoImpl());
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		// 响应查询操作
		if ("query".equals(flag)) {
			String name = request.getParameter("billname");
			String payed = request.getParameter("payed");
			if (name == null) {
				name = "";
			}
			if (payed == null) {
				payed = "";
			}
			List<Bill> bills = bsi.query(name, payed);
			request.setAttribute("bills", bills);
			if (bills.size() == 0) {
				String msg = "<script type=\"text/javascript\">alert(\"没有查询到结果！\");history.go(-1);</script>";
				out.print(msg);
				out.flush();
			} else {
				request.getRequestDispatcher("bill_query.jsp").forward(request,
						response);
			}
		} else if ("add".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("billNum"));
			String name = request.getParameter("name");
			int amount = Integer.parseInt(request.getParameter("amount"));
			double money = Double.parseDouble(request.getParameter("money"));
			String unit = request.getParameter("unit");
			int payed = Integer.parseInt(request.getParameter("payed"));
			int supplier_id = Integer
					.parseInt(request.getParameter("supplier"));
			String description = request.getParameter("description");

			// 封装成账单信息对象
			Bill bill = new Bill();
			bill.setAmount(amount);
			bill.setDescription(description);
			bill.setId(id);
			bill.setMoney(money);
			bill.setName(name);
			bill.setPayed(payed);
			bill.setSupplier_id(supplier_id);
			bill.setUnit(unit);
			boolean flag2 = bsi.add(bill);
			if (flag2) {
				session.setAttribute("bills", bsi.getBillList());
				out.println("<script language=\"javascript\">alert(\"添加成功！\");	window.location=\"bill.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"添加失败！\");	history.go(-1);</script>");

			}
			out.flush();
		} else if ("edit".equals(flag)) {
			// 找到需要修改的订单对象
			String id = request.getParameter("row");
			List<Bill> bills = (List<Bill>) session.getAttribute("bills");
			Bill b = null;
			for (Bill bill : bills) {
				if (bill.getId() == Integer.parseInt(id)) {
					b = bill;
					request.setAttribute("bill", b);
					break;
				}
			}
			request.getRequestDispatcher("bill_edit.jsp").forward(request,
					response);

		} else if ("revise".equals(flag)) {
			// 找到需要修改的订单对象
			String id = request.getParameter("row");
			List<Bill> bills = (List<Bill>) session.getAttribute("bills");
			Bill b = null;
			for (Bill bill : bills) {
				if (bill.getId() == Integer.parseInt(id)) {
					b = bill;
					request.setAttribute("bill", b);
					break;
				}
			}
			request.getRequestDispatcher("bill_revise.jsp").forward(request,
					response);

		} else if ("dorevise".equals(flag)) {
			// 修改订单对象
			String reviseid = request.getParameter("reviseid");
			if (reviseid != null) {
				int id = Integer.parseInt(request.getParameter("billNum"));
				String name = request.getParameter("name");
				int amount = Integer.parseInt(request.getParameter("amount"));
				double money = Double
						.parseDouble(request.getParameter("money"));
				String unit = request.getParameter("unit");
				int payed = Integer.parseInt(request.getParameter("payed"));
				int supplier_id = Integer.parseInt(request
						.getParameter("supplier"));
				String description = request.getParameter("description");
				// 封装成账单信息对象
				Bill bill = new Bill();
				bill.setAmount(amount);
				bill.setDescription(description);
				bill.setId(id);
				bill.setMoney(money);
				bill.setName(name);
				bill.setPayed(payed);
				bill.setSupplier_id(supplier_id);
				bill.setUnit(unit);
				boolean flag2 = bsi.update(bill, reviseid);
				if (flag2) {
					session.setAttribute("bills", bsi.getBillList());

					out.println("<script language=\"javascript\">alert(\"修改成功！\");	window.location=\"bill.do?flag=list\";</script>");

				} else {
					out.println("<script language=\"javascript\">alert(\"修改失败！\");	history.go(-1);</script>");

				}
			}
		} else if ("delete".equals(flag)) {
			// 删除操作
			String row = request.getParameter("row");

			boolean flag2 = bsi.delete(row);
			if (flag2) {
				session.setAttribute("bills", bsi.getBillList());
				out.println("<script language=\"javascript\">alert(\"删除成功！\");	window.location=\"bill.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"删除失败！\");	history.go(-1);</script>");

			}
			out.flush();
		} else if ("list".equals(flag)) {
			Page page = new Page();
			int currPage = Integer
					.parseInt(request.getParameter("page") == null ? "1"
							: request.getParameter("page"));
			int billPageSize = page.billPageSize;
			int billCount = bsi.getTotalCount();
			page.setPageSize(billPageSize);
			page.setRecordCount(billCount);
			int billPageCount = page.getTotalPageCountByRs();

			List<Bill> bills = bsi.getByPage(currPage, billPageSize);
			request.setAttribute("bills", bills);
			request.setAttribute("currPage", currPage);
			request.setAttribute("billCount", billCount);
			request.setAttribute("billPageCount", billPageCount);
			request.getRequestDispatcher("admin_bill_list.jsp").forward(
					request, response);
		}
	}
}
