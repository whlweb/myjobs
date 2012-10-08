package service.impl;


import java.util.List;

import service.BillService;

import dao.BillDao;

import entity.Bill;

public class BillServiceImpl implements BillService {
	private BillDao billdao;
	
   @Override
	public List<Bill> getBillList() {
	   return billdao.getBillList();
	}

	@Override
	public boolean add(Bill bill) {
		return billdao.add(bill);
	}

	@Override
	public boolean delete(String id) {
		return billdao.delete(id);
	}

	@Override
	public List<Bill> query(String q_name, String q_payed) {
		return billdao.query(q_name, q_payed);
	}

	@Override
	public boolean update(Bill bill, String id) {
		return billdao.update(bill, id);
	}
	
	
	 public BillDao getBilldao() {
			return billdao;
		}

	public void setBilldao(BillDao billdao) {
			this.billdao=billdao;
		}

	@Override
	public int getTotalCount() {
		return billdao.getTotalCount();
	}

	@Override
	public List<Bill> getByPage(int pageNo, int pageSize) {
		return billdao.getByPage(pageNo, pageSize);
	}

	
}
