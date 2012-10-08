package test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Student;

public class SQLTest {
	@SuppressWarnings("unchecked")
	public static void test() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			String sql="select s.* from student s";
			SQLQuery sq=session.createSQLQuery(sql).addEntity("s",Student.class);
			List<Student> students=sq.list();
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
	

	public static void main(String[] args) {
		test();
	}


}
