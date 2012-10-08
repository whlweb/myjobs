package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Supplier;

public class SupplierDao extends BaseDAO {
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
			String sql = "select * from suppliers where name like '%" + q_name
					+ "%' and description like '%"+q_description+"%'";
			Object[] params = {};
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
			String sql = "update suppliers set id=?,name=?, description=?, contact=?, phone=?, fax=?, address=? where id="
					+ id;
			Object[] params = { supplier.getId(), supplier.getName(),
					supplier.getDescription(), supplier.getContact(), supplier.getPhone(),
					supplier.getFax(), supplier.getAddress() };
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


}
