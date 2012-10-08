package annotations;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;

public class Test {
	public static void test() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Weapon w = new Weapon();
			w.setName("gun5");
			session.save(w);

			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	@SuppressWarnings("unchecked")
	public static void test2() {
		Session session = HibernateSessionFactory.getSession();
		Query q=session.getNamedQuery("namedQuery");
		q.setLong("wid", 3);
		List<Weapon> ws = q.list();
		for (Weapon w : ws) {
			System.out.println(w.getName());

		}
		HibernateSessionFactory.closeSession();

	}

	@SuppressWarnings("unchecked")
	public static void test3() {
		Session session = HibernateSessionFactory.getSession();
		Query q = session.getNamedQuery("nativeQuery");
		q.setLong("wid", 3);

		List<Weapon> ws = q.list();
		for (Weapon w : ws) {
			System.out.println(w.getName());

		}
		HibernateSessionFactory.closeSession();

	}

	public static void main(String[] args) {
		test3();

	}

}
