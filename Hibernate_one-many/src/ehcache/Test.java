package ehcache;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;

public class Test {
	public static void test(){
		Session session=HibernateSessionFactory.getSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			Bird b=(Bird) session.load(Bird.class, 1);
			b.setName("a");
			Bird b2= new Bird();
			b2.setName("c");
			session.save(b2);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		test();
	}

}
