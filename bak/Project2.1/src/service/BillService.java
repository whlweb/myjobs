package service;

import java.util.List;

import entity.Bill;

public interface BillService {
	// 查询账单信息,以创建时间远近逆序排列
	public List<Bill> getBillList();
	

	// 增加账单信息
	public boolean add(Bill bill);

	// 删除账单信息
	public boolean delete(String id) ;
	
	// 组合查询
	public List<Bill> query(String q_name,String q_payed); 

	// 修改账单
	public boolean update(Bill bill,String id);
	
}

