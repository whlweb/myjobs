package service;


import java.util.List;

import entity.Supplier;

public interface SupplierService  {

	

	public List<Supplier> getSupplierList() ;
	// 查询
	public List<Supplier> query(String q_name,String q_description) ;
	// 增加供应商信息
	public boolean add(Supplier supplier);
	// 删除供应商信息
	public boolean delete(String id) ;
	// 修改供应商
	public boolean update(Supplier supplier, String id) ;

}
