package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import util.Env;

public class BaseDAO {
	protected Connection con;
	protected PreparedStatement ps;
	protected Statement stmt;
	protected ResultSet rs;

	public  Connection getConnection(){
		Properties p = Env.getInstance();
		try {
			Class.forName(p.getProperty("driver"));
			con = DriverManager.getConnection(p.getProperty("url"),
					p.getProperty("id"), p.getProperty("password"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
		

	}

	// 增删改
	public int executeUpdate(String sql, Object[] params) {
		int updateRows = 0;
		getConnection();
		try {
			ps=con.prepareStatement(sql);
			//填充占位符
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			updateRows=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateRows;
	}

	// 查询
	public ResultSet executeSQL(String sql,Object[] params) {
		getConnection();
		try {
			ps=con.prepareStatement(sql);
			//填充占位符
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 关闭资源
	public boolean closeResource() {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
