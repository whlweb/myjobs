package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import po.Login;
import util.HibernateSessionFactory;

@SuppressWarnings("rawtypes")
public class LoginDAO {

	public void save(Login login) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			session.save(login);
			tran.commit();
			System.out.println("添加数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public void update(Login login) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			session.update(login);
			tran.commit();
			System.out.println("修改数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public void delete(Login login) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			session.delete(login);
			tran.commit();
			System.out.println("删除数据成功！");
		} catch (HibernateException e) {
			tran.rollback();
			System.err.println(e);
		}

		HibernateSessionFactory.closeSession();

	}

	public List queryAll() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Login");
		List list = query.list();
		HibernateSessionFactory.closeSession();
		return list;

	}

	public Login queryByUsername(String username) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Login l where l.username=?");
		query.setString(0, username);
		Login login = (Login) query.uniqueResult();
		HibernateSessionFactory.closeSession();
		return login;

	}

	public int saveBlob(Login login, String path) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		InputStream is = null;
		try {
			is=new FileInputStream(path);
			tran = session.beginTransaction();
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			login.setImage(session.getLobHelper().createBlob(buffer));
			session.save(login);
			tran.commit();
		} catch (IOException e) {
			tran.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();

			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

		return login.getId();

	}
	
	public void getBlob(int id,String path){
		Session session=HibernateSessionFactory.getSession();
		Login login=(Login)session.get(Login.class, id);
		Blob image=login.getImage();
		InputStream is=null;
		OutputStream os=null;
		try {
			is=image.getBinaryStream();
			os=new FileOutputStream(path);
			int count=-1;
			byte[] temp=new byte[1000];
			while((count=is.read(temp))!=-1){
				os.write(temp, 0, count);
			}
			os.flush();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public int saveClob(Login login, String description) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			login.setDescription(session.getLobHelper().createClob(description));
			session.save(login);
			tran.commit();
		} catch (HibernateException e) {
			tran.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();	
		}

		return login.getId();

	}
	
	public Clob getClob(int id){
		Session session=HibernateSessionFactory.getSession();
		Login login =(Login) session.get(Login.class, id);
		
		
		
		return login.getDescription();
		
	}
}
