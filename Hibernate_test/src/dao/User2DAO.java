package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import set.User2;
import util.HibernateSessionFactory;

public class User2DAO {

	public void save(User2 user) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			session.save(user);
			tran.commit();
			System.out.println("添加数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}
	
	public User2 loadUser(int id){
		Session session=HibernateSessionFactory.getSession();
		User2 user=(User2) session.load(User2.class, id);
		return user;
		
	}
	
	public void printUser(User2 user){
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		List<String> images=user.getImages();
		for (String path : images) {
			System.out.println(path);
			
		}
	}

	
}
