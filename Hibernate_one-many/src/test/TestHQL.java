package test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Student;

import util.HibernateSessionFactory;
@SuppressWarnings("unchecked")

public class TestHQL {
	
	public static void test1() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query q=session.createQuery("from Student where sid like :sid");
			q.setParameter("sid", 3);
			List<Student> list=q.list();
			for (Student student : list) {
				System.out.println(student.getSid()+"  "+student.getName());
			}


			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}
	
	public static void test2() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query q=session.createQuery("update Student set name='Jim' where name='Jack'");
			q.executeUpdate();

			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}
	
	public static void test3() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query q=session.createQuery("select count(gid) from Grade");
			Long size=(Long) q.uniqueResult();
			System.out.println(size);

			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public static void main(String[] args) {
		test1();

	}

}
