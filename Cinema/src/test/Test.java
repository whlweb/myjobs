package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.FilmInfo;
import entity.FilmType;

public class Test {
	public static void test() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			FilmType ft=(FilmType) session.load(FilmType.class, new Long(6));
			FilmInfo f=new FilmInfo();
			f.setFilmtype(ft);
			f.setName("Ð¡µº¾ª»ê");
			f.setActor("ÄÝ¿É¡¤»ùµÂÂü");
			f.setDirector("°¢Âü°Í");
			f.setPrice(80);
			session.save(f);
			tran.commit();
			System.out.println("Job Done!");
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
