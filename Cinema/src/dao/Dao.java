package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.FilmInfo;
import entity.FilmType;

@SuppressWarnings("unchecked")
public class Dao {

	public List<FilmInfo> getAll() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from FilmInfo";
		Query query = session.createQuery(hql);
		List<FilmInfo> films = query.list();
		HibernateSessionFactory.closeSession();
		return films;
	}

	public List<FilmInfo> query(String name, String tid, String actor,
			String director, String price1, String price2) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from FilmInfo where 1=1";
		if (name != null && !"".equals(name)) {
			hql = hql + " and name like '%" + name + "%'";
		}
		if (tid != null && !"".equals(tid)) {
			hql = hql + " and filmtype.tid like '%" + tid + "%'";
		}
		if (actor != null && !"".equals(actor)) {
			hql = hql + " and actor like '%" + actor + "%'";
		}
		if (director != null && !"".equals(director)) {
			hql = hql + " and director like '%" + director + "%'";
		}
		if (price1 != null && !"".equals(price1)) {
			hql = hql + " and price >= " + price1;
		}
		if (price2 != null && !"".equals(price2)) {
			hql = hql + " and price <=" + price2;
		}
		Query q = session.createQuery(hql);

		List<FilmInfo> films = q.list();
		HibernateSessionFactory.closeSession();
		return films;

	}

	public void add(FilmInfo f) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			session.save(f);
			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public List<FilmType> getTypes() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from FilmType";
		Query query = session.createQuery(hql);
		List<FilmType> types = query.list();
		HibernateSessionFactory.closeSession();
		return types;
	}

}
