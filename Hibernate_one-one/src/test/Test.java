package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Car;
import entity.License;

public class Test {

	public static void save() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Car car=new Car();
			car.setCid(1001);
			car.setName("Test Car1");
			car.setBrand("Honda");
		
			License license =new License();
			license.setLid(12345);
			license.setDescription("Number plate of Test Car1");
			
//			car.setLicense(license);
			license.setCar(car);
			session.save(license);
			
			tran.commit();
			System.out.println("添加数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}
	

	
	public static void main(String[] args) {
		save();
//		find();
//		add();
//		getGrade();
	}

}
