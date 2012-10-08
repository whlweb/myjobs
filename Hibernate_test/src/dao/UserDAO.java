package dao;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import set.User;
import util.HibernateSessionFactory;

public class UserDAO {

	public void save(User user) {
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
	
	public User loadUser(int id){
		Session session=HibernateSessionFactory.getSession();
		User user=(User) session.load(User.class, id);
		return user;
		
	}
	
	public void printUser(User user){
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		Set<String> images=user.getImages();
		for (String path : images) {
			System.out.println(path);
			
		}
	}

	
}
