package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Card;
import entity.Course;
import entity.Grade;
import entity.Student;

public class Test {

	public static void save() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Grade grade = new Grade();
			grade.setGid(200701);
			grade.setName("Grade1");
			grade.setG_desc("Grade Test1");
			Student stu1 = new Student();
			stu1.setName("Jack");
			stu1.setGender("M");
			Student stu2 = new Student();
			stu2.setName("Rose");
			stu2.setGender("F");
			Student stu3 = new Student();
			stu3.setName("Jane");
			stu3.setGender("F");

			stu1.setGrade(grade);
			stu2.setGrade(grade);
			stu3.setGrade(grade);
			// grade.getStudents().add(stu1);
			// grade.getStudents().add(stu2);
			// grade.getStudents().add(stu3);

			// session.save(grade);
			session.save(stu1);
			session.save(stu2);
			session.save(stu3);
			tran.commit();
			System.out.println("添加数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public static void delete() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Grade grade = (Grade) session.get(Grade.class, 200701);
			if (grade != null) {
				session.delete(grade);
			}
			tran.commit();
			System.out.println("删除数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public static void getStudent() {
		Session session = HibernateSessionFactory.getSession();
		Grade g = (Grade) session.get(Grade.class, 200701);
		System.out.println(g.getName());
		System.out.println(g.getG_desc());
		for (Student s : g.getStudents()) {
			System.out.println(s.getName());
			System.out.println(s.getGender());
		}

	}

	public static void getGrade() {
		Session session = HibernateSessionFactory.getSession();
		Student s = (Student) session.get(Student.class, 1);
		System.out.println(s.getGrade().getName());
		System.out.println(s.getGrade().getG_desc());
	}

	public static void add() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Student s = new Student();
			s.setName("Jim");
			s.setGender("M");
			Grade g = (Grade) session.get(Grade.class, 200701);
			session.save(s);
			g.getStudents().add(s);

			session.update(g);
			tran.commit();
			System.out.println("修改数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();
	}

	public static void update() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Grade grade = new Grade();
			grade.setGid(200702);
			grade.setName("Grade2");
			grade.setG_desc("Grade Test2");

			Student stu2 = (Student) session.get(Student.class, 2);
			grade.getStudents().add(stu2);

			session.save(grade);

			tran.commit();
			System.out.println("添加数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();
	}

	public static void saveCard() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Card card = new Card();
			card.setDescription("Student ID Card1");
			card.setStudent((Student) session.load(Student.class, 1));
			Card card2 = new Card();
			card2.setDescription("Student ID Card2");
			card2.setStudent((Student) session.load(Student.class, 2));
			Card card3 = new Card();
			card3.setDescription("Student ID Card3");
			card3.setStudent((Student) session.load(Student.class, 3));
			session.save(card);
			session.save(card2);
			session.save(card3);

			tran.commit();
			System.out.println("添加数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public static void saveCourse() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Student stu1 = (Student) session.load(Student.class, 1);
			Student stu2 = (Student) session.load(Student.class, 2);
			Student stu3 = (Student) session.load(Student.class, 3);

			Course course1 = new Course();
			course1.setName("数学");
			course1.getStudents().add(stu2);
			course1.getStudents().add(stu3);

			Course course2 = new Course();
			course2.setName("物理");
			course2.getStudents().add(stu1);
			course2.getStudents().add(stu3);

			Course course3 = new Course();
			course3.setName("化学");
			course3.getStudents().add(stu1);
			course3.getStudents().add(stu2);

			session.save(course1);
			session.save(course2);
			session.save(course3);

			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public static void test() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Grade grade = new Grade();
			grade.setGid(200703);
			grade.setName("Grade3");
			grade.setG_desc("Grade Test3");
			
			Student stu5 = new Student();
			stu5.setName("Amanda");
			stu5.setGender("F");
			session.save(stu5);
			session.save(grade);
			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public static void main(String[] args) {
		// delete();
		// save();
		// getStudent();
		// getGrade();
		// saveCard();
//		test();
	}

}
