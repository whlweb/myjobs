package test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import util.HibernateSessionFactory;
import entity.Student;

public class QBCTest {
	@SuppressWarnings("unchecked")
	public static void test() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Criteria c=session.createCriteria(Student.class);
			c.add(Restrictions.like("sid", 1));
			c.addOrder(Order.asc("sid"));
			List<Student> students=c.list();
			for (Student s : students) {
				System.out.println(s.getSid()+"  "+s.getName()+"  "+s.getGender());
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
			Criteria c=session.createCriteria(Student.class);
			Student stu=new Student();
			stu.setGender("F");
			c.add(Example.create(stu));
			@SuppressWarnings("unchecked")
			List<Student> students=c.list();
			for (Student s : students) {
				System.out.println(s.getSid()+"  "+s.getName()+"  "+s.getGender());
			}
			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}
	@SuppressWarnings("unchecked")
	public static void test3() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Criteria c=session.createCriteria(Student.class);
			c.setProjection(Projections.groupProperty("gender"));
			
			for (Iterator<String> it=c.list().iterator(); it.hasNext();) {
				System.out.println(it.next());;
				
			}

			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}
	
	public static void main(String[] args) {
		test();
	}


}
