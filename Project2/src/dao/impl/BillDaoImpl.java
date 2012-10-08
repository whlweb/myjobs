package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Page;
import dao.BaseDAO;
import dao.BillDao;
import entity.Bill;

public class BillDaoImpl extends BaseDAO implements BillDao {
	// 查询账单信息,以创建时间远近逆序排列
	public List<Bill> getBillList() {
		List<Bill> billList = new ArrayList<Bill>();
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from bills where isdeleted=0 order by time desc";
			Object[] params = {};
			ResultSet rs = this.executeSQL(sql, params);
			// （4）处理执行结果(ResultSet)，
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int amount = rs.getInt("amount");
				double money = rs.getDouble("money");
				String unit = rs.getString("unit");
				int payed = rs.getInt("payed");
				int supplier_id = rs.getInt("supplier_id");
				Timestamp time = rs.getTimestamp("time");
				int isDeleted = rs.getInt("isdeleted");
				String description = rs.getString("description");

				// 封装成账单信息对象
				Bill bill = new Bill();
				bill.setAmount(amount);
				bill.setDate(time);
				bill.setDescription(description);
				bill.setId(id);
				bill.setIsDeleted(isDeleted);
				bill.setMoney(money);
				bill.setName(name);
				bill.setPayed(payed);
				bill.setSupplier_id(supplier_id);
				bill.setUnit(unit);
				// 将账单对象放进集合中
				billList.add(bill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return billList;
	}

	// 增加账单信息
	public boolean add(Bill bill) {
		boolean flag = false;
		try {
			String sql = "insert into bills values(?,?,?,?,?,?,?,?,?,?)";
			Object[] params = { bill.getId(), bill.getName(), bill.getAmount(),
					bill.getMoney(), bill.getUnit(), bill.getPayed(),
					bill.getSupplier_id(),
					new Timestamp((new Date()).getTime()), bill.getIsDeleted(),
					bill.getDescription() };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("插入账单成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}

	// 删除账单信息
	public boolean delete(String id) {
		boolean flag = false;
		try {
			String sql = "update bills set isdeleted=1 where id=?";
			Object[] params = { id };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("删除账单成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}
	
	// 组合查询
	public List<Bill> query(String q_name,String q_payed) {
		List<Bill> billList = new ArrayList<Bill>();
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from bills where isdeleted=0 and name like ? and payed like ?";
			Object[] params={"%"+q_name+"%","%"+q_payed+"%"};
			ResultSet rs = this.executeSQL(sql, params);
			// （4）处理执行结果(ResultSet)，
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int amount = rs.getInt("amount");
				double money = rs.getDouble("money");
				String unit = rs.getString("unit");
				int payed = rs.getInt("payed");
				int supplier_id = rs.getInt("supplier_id");
				Timestamp time = rs.getTimestamp("time");
				int isDeleted = rs.getInt("isdeleted");
				String description = rs.getString("description");

				// 封装成账单信息对象
				Bill bill = new Bill();
				bill.setAmount(amount);
				bill.setDate(time);
				bill.setDescription(description);
				bill.setId(id);
				bill.setIsDeleted(isDeleted);
				bill.setMoney(money);
				bill.setName(name);
				bill.setPayed(payed);
				bill.setSupplier_id(supplier_id);
				bill.setUnit(unit);
				// 将账单对象放进集合中
				billList.add(bill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return billList;
	}	

	// 修改账单
	public boolean update(Bill bill,String id) {
		boolean flag = false;
		try {
			String sql = "update bills set id=?,name=?, money=?, unit=?, amount=?, description=?, supplier_id=?, payed=? where id=?";
			Object[] params = { bill.getId(), bill.getName(), bill.getMoney(),
					bill.getUnit(), bill.getAmount(), bill.getDescription(),
					bill.getSupplier_id(), bill.getPayed(),id};
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("修改账单成功！");
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
		String sql="select count(*) from bills where isdeleted=0";
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
	 * 分页获取账单信息
	 */
	public List<Bill> getByPage(int pageNo, int pageSize) {
		List<Bill> billList=new ArrayList<Bill>();
		String sql="SELECT * FROM (SELECT b.*,ROWNUM rn FROM bills b where isdeleted=0) a WHERE a.rn>=? AND a.rn<=? ";
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
				int amount = rs.getInt("amount");
				double money = rs.getDouble("money");
				String unit = rs.getString("unit");
				int payed = rs.getInt("payed");
				int supplier_id = rs.getInt("supplier_id");
				Timestamp time = rs.getTimestamp("time");
				int isDeleted = rs.getInt("isdeleted");
				String description = rs.getString("description");

				// 封装成账单信息对象
				Bill bill = new Bill();
				bill.setAmount(amount);
				bill.setDate(time);
				bill.setDescription(description);
				bill.setId(id);
				bill.setIsDeleted(isDeleted);
				bill.setMoney(money);
				bill.setName(name);
				bill.setPayed(payed);
				bill.setSupplier_id(supplier_id);
				bill.setUnit(unit);
				// 将账单对象放进集合中
				billList.add(bill);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return billList;
	}

}
