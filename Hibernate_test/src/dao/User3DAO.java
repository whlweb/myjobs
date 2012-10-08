package dao;

import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import set.User3;
import util.HibernateSessionFactory;

public class User3DAO {

	public void save(User3 user) {
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
	
	public User3 loadUser(int id){
		Session session=HibernateSessionFactory.getSession();
		User3 user=(User3) session.load(User3.class, id);
		return user;
		
	}
	
	public void printUser(User3 user){
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		Map images=user.getImages();
		Set<String> keys=images.keySet();
		for (String key : keys) {
			System.out.println(images.get(key));
		}


	}

	
}
