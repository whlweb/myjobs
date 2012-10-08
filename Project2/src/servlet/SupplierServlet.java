package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.SupplierServiceImpl;
import util.Page;
import dao.impl.SupplierDaoImpl;
import entity.Supplier;

@SuppressWarnings("serial")
public class SupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SupplierServlet() {
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
		String role = (String) request.getSession().getAttribute("role");
		if(!role.equals("admin")){
			out.println("<script language=\"javascript\">alert(\"非法操作！\");	window.location=\"bill.do?flag=list\";</script>");
	
		}
		SupplierServiceImpl ssi = new SupplierServiceImpl();
		ssi.setSd(new SupplierDaoImpl());
		String flag = request.getParameter("flag");
		// 响应查询操作
		if ("query".equals(flag)) {
			String q_name = request.getParameter("name");
			String q_description = request.getParameter("description");
			List<Supplier> suppliers = ssi.query(q_name, q_description);
			request.setAttribute("suppliers", suppliers);
			if (suppliers.size() == 0) {
				out.println("<script language=\"javascript\">alert(\"没有查询到相应的供应商，请重试！\");	history.go(-1);</script>");
				out.flush();
			} else {
				request.getRequestDispatcher("supplier_query.jsp").forward(
						request, response);
			}
		} else if ("add".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			String fax = request.getParameter("fax");
			String address = request.getParameter("address");

			// 封装成供应商信息对象
			Supplier supplier = new Supplier();
			supplier.setAddress(address);
			supplier.setContact(contact);
			supplier.setDescription(description);
			supplier.setFax(fax);
			supplier.setId(id);
			supplier.setName(name);
			supplier.setPhone(phone);
			boolean flag2 = ssi.add(supplier);
			if (flag2) {
				request.getSession().setAttribute("suppliers",
						ssi.getSupplierList());
				out.println("<script language=\"javascript\">alert(\"添加成功！\");	window.location=\"supplier.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"添加失败！\");	history.go(-1);</script>");

			}
			out.flush();
		} else if ("edit".equals(flag)) {
			// 找到需要修改的供应商对象
			String id = request.getParameter("row");
			List<Supplier> suppliers = (List<Supplier>) request.getSession()
					.getAttribute("suppliers");
			Supplier s = null;
			for (Supplier supplier : suppliers) {
				if (supplier.getId() == Integer.parseInt(id)) {
					s = supplier;
					request.setAttribute("supplier", s);
					break;
				}
			}
			request.getRequestDispatcher("supplier_edit.jsp").forward(request,
					response);

		} else if ("revise".equals(flag)) {
			String id = request.getParameter("row");
			List<Supplier> suppliers = (List<Supplier>) request.getSession()
					.getAttribute("suppliers");
			Supplier s = null;
			for (Supplier supplier : suppliers) {
				if (supplier.getId() == Integer.parseInt(id)) {
					s = supplier;
					request.setAttribute("supplier", s);
					break;
				}
			}
			request.getRequestDispatcher("supplier_revise.jsp").forward(
					request, response);
		} else if ("dorevise".equals(flag)) {
			// 修改订单对象
			String reviseid = request.getParameter("reviseid");

			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			String fax = request.getParameter("fax");
			String address = request.getParameter("address");

			// 封装成供应商信息对象
			Supplier supplier = new Supplier();
			supplier.setAddress(address);
			supplier.setContact(contact);
			supplier.setDescription(description);
			supplier.setFax(fax);
			supplier.setId(id);
			supplier.setName(name);
			supplier.setPhone(phone);
			boolean flag2 = ssi.update(supplier, reviseid);
			if (flag2) {
				request.getSession().setAttribute("suppliers",
						ssi.getSupplierList());
				out.println("<script language=\"javascript\">alert(\"修改成功！\");	window.location=\"supplier.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"修改失败！\");	history.go(-1);</script>");

			}

		} else if ("delete".equals(flag)) {
			// 删除操作
			String row = request.getParameter("row");

			boolean flag2 = ssi.delete(row);
			if (flag2) {
				request.getSession().setAttribute("suppliers",
						ssi.getSupplierList());
				out.println("<script language=\"javascript\">alert(\"供应商删除成功！\");	window.location=\"supplier.do?flag=list\";</script>");

			} else {
				out.println("<script language=\"javascript\">alert(\"供应商删除失败！\");	history.go(-1);</script>");

			}
			out.flush();
		}else if("list".equals(flag)){
			Page page=new Page();
			int currPage = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
			int supplierPageSize=page.supplierPageSize;
			page.setPageSize(supplierPageSize);
			int supplierCount=ssi.getTotalCount();
			page.setRecordCount(supplierCount);
			int supplierPageCount=page.getTotalPageCountByRs();
			
			List<Supplier> suppliers = ssi
					.getByPage(currPage, supplierPageSize);
			request.setAttribute("suppliers", suppliers);
			request.setAttribute("currPage", currPage);
			request.setAttribute("supplierCount", supplierCount);
			request.setAttribute("supplierPageCount", supplierPageCount);
			request.getRequestDispatcher("supplierAdmin.jsp").forward(request, response);
		}
	}
}
