package service.impl;


import java.util.List;

import service.SupplierService;

import dao.SupplierDao;

import entity.Supplier;

public class SupplierServiceImpl  implements SupplierService{
	private SupplierDao sd;

	@Override
	public List<Supplier> getSupplierList() {
		return sd.getSupplierList();
	}

	@Override
	public List<Supplier> query(String q_name, String q_description) {
		return sd.query(q_name, q_description);
	}

	@Override
	public boolean add(Supplier supplier) {
		return sd.add(supplier);
	}

	@Override
	public boolean delete(String id) {
		return sd.delete(id);
	}

	@Override
	public boolean update(Supplier supplier, String id) {
		return sd.update(supplier, id);
	}

	public SupplierDao getSd() {
		return this.sd;
	}

	public void setSd(SupplierDao sd) {
		this.sd = sd;
	}

	

	

}
