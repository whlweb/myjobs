package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Page;

import dao.BaseDAO;
import dao.SupplierDao;

import entity.Supplier;

public class SupplierDaoImpl extends BaseDAO  implements SupplierDao{
	private ResultSet rs;

	

	public List<Supplier> getSupplierList() {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from suppliers order by id";
			Object[] params = {};
			rs = this.executeSQL(sql, params);
			// （4）处理执行结果(ResultSet)，
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String contact = rs.getString("contact");
				String phone= rs.getString("phone");
				String fax = rs.getString("fax");
				String address = rs.getString("address");

				// 封装成供应商信息对象
				Supplier supplier = new Supplier();
				supplier.setAddress(address);
				supplier.setContact(contact);
				supplier.setDescription(description);
				supplier.setFax(fax);
				supplier.setId(id);
				supplier.setName(name);
				supplier.setPhone(phone);

				// 将供应商对象放进集合中
				supplierList.add(supplier);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return supplierList;
	}

	// 查询
	public List<Supplier> query(String q_name,String q_description) {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from suppliers where name like ? and description like ?";
			Object[] params = {"%"+q_name+"%","%"+q_description+"%"};
			ResultSet rs = this.executeSQL(sql, params);
			// （4）处理执行结果(ResultSet)，
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String contact = rs.getString("contact");
				String phone= rs.getString("phone");
				String fax = rs.getString("fax");
				String address = rs.getString("address");

				// 封装成供应商信息对象
				Supplier supplier = new Supplier();
				supplier.setAddress(address);
				supplier.setContact(contact);
				supplier.setDescription(description);
				supplier.setFax(fax);
				supplier.setId(id);
				supplier.setName(name);
				supplier.setPhone(phone);
				// 将供应商对象放进集合中
				supplierList.add(supplier);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return supplierList;
	}

	// 增加供应商信息
	public boolean add(Supplier supplier) {
		boolean flag = false;
		try {
			String sql = "insert into suppliers values(?,?,?,?,?,?,?)";
			Object[] params = { supplier.getId(), supplier.getName(),
					supplier.getDescription(), supplier.getContact(), supplier.getPhone(),
					supplier.getFax(), supplier.getAddress() };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("插入供应商成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}

	// 删除供应商信息
	public boolean delete(String id) {
		boolean flag = false;
		try {
			String sql = "delete from suppliers where id=?";
			Object[] params = { id };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("删除供应商成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}

	// 修改供应商
	public boolean update(Supplier supplier, String id) {
		boolean flag = false;
		try {
			String sql = "update suppliers set id=?,name=?, description=?, contact=?, phone=?, fax=?, address=? where id=?";
			Object[] params = { supplier.getId(), supplier.getName(),
					supplier.getDescription(), supplier.getContact(), supplier.getPhone(),
					supplier.getFax(), supplier.getAddress(),id };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("修改供应商成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}
	
	public int getTotalCount() {
		int totalCount=0;
		String sql="select count(*) from suppliers";
		Object[]params={};
		ResultSet rs=this.executeSQL(sql, params);
		try {
			while(rs.next()){
				totalCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return totalCount;
	}
	
	/**
	 * 分页获取供应商信息
	 */
	public List<Supplier> getByPage(int pageNo, int pageSize) {
		List<Supplier> supplierList=new ArrayList<Supplier>();
		String sql="SELECT * FROM (SELECT b.*,ROWNUM rn FROM suppliers b) a WHERE a.rn>=? AND a.rn<=? ";
		Page page=new Page();
		page.setCurrPageNo(pageNo);//设置当前页码
		page.setPageSize(pageSize);//每页显示记录数
		//计算sql语句的起始记录数以及结束记录数的行数
		int startRow=page.getStartRow();
		int endRow=page.getEndRow();
		Object[]params={startRow,endRow};
		ResultSet rs=this.executeSQL(sql, params);
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String contact = rs.getString("contact");
				String phone= rs.getString("phone");
				String fax = rs.getString("fax");
				String address = rs.getString("address");

				// 封装成供应商信息对象
				Supplier supplier = new Supplier();
				supplier.setAddress(address);
				supplier.setContact(contact);
				supplier.setDescription(description);
				supplier.setFax(fax);
				supplier.setId(id);
				supplier.setName(name);
				supplier.setPhone(phone);
				// 将供应商对象放进集合中
				supplierList.add(supplier);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return supplierList;
	}



}
