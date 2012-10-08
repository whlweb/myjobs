package dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BaseDAO;
import dao.UserDao;

import entity.User;

public class UserDaoImpl extends BaseDAO implements UserDao  {

	public User login(String username, String password) {// 用户登录方法
		User user = null;
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from users where username=? and password=?";
			Object[] params = {username,password};
			ResultSet rs = this.executeSQL(sql, params);

			while (rs.next()) {
				int id = rs.getInt("id");
				int gender = rs.getInt("gender");
				int age = rs.getInt("age");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");

				// 封装成用户信息对象
				user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setGender(gender);
				user.setId(id);
				user.setPassword(password);
				user.setPhone(phone);
				user.setRole(role);
				user.setUsername(username);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeResource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
		
	}

	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from users";
			Object[] params = {};
			ResultSet rs = this.executeSQL(sql, params);
			// （4）处理执行结果(ResultSet)，
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int gender = rs.getInt("gender");
				int age = rs.getInt("age");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");

				// 封装成用户信息对象
				User user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setGender(gender);
				user.setId(id);
				user.setPassword(password);
				user.setPhone(phone);
				user.setRole(role);
				user.setUsername(username);
				// 将用户对象放进集合中
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return userList;
	}

	// 查询
	public List<User> query(String q_name) {
		List<User> userList = new ArrayList<User>();
		try {
			// （3）获得Statement对象，执行SQL语句
			String sql = "select * from users where username like '%?%'";
			Object[] params = {q_name};
			ResultSet rs = this.executeSQL(sql, params);
			// （4）处理执行结果(ResultSet)，
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int gender = rs.getInt("gender");
				int age = rs.getInt("age");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");

				// 封装成用户信息对象
				User user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setGender(gender);
				user.setId(id);
				user.setPassword(password);
				user.setPhone(phone);
				user.setRole(role);
				user.setUsername(username);
				// 将用户对象放进集合中
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return userList;
	}

	// 增加用户信息
	public boolean add(User user) {
		boolean flag = false;
		try {
			String sql = "insert into users values(?,?,?,?,?,?,?,?)";
			Object[] params = { user.getId(), user.getUsername(),
					user.getPassword(), user.getGender(), user.getAge(),
					user.getPhone(), user.getAddress(), user.getRole() };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("插入用户成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}

	// 删除用户信息
	public boolean delete(String id) {
		boolean flag = false;
		try {
			String sql = "delete from users where id=?";
			Object[] params = { id };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("删除用户成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}

	// 修改用户
	public boolean update(User user, String id) {
		boolean flag = false;
		try {
			String sql = "update users set id=?,username=?, gender=?, age=?, phone=?, address=?, role=? where id="
					+ id;
			Object[] params = { user.getId(), user.getUsername(),
					user.getGender(), user.getAge(), user.getPhone(),
					user.getAddress(), user.getRole() };
			int i = this.executeUpdate(sql, params);
			// （4）处理执行结果
			if (i > 0) {
				System.out.println("修改用户成功！");
			}
			flag = true;
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}

	public boolean changepw(String id, String oldpw, String newpw) {
		boolean flag = false;
		try {
			// 首先验证旧密码是否正确
			String sql = "select password from users where id=?";
			Object[] params = {id};
			rs = this.executeSQL(sql, params);
			if (!rs.next() || !rs.getString("password").equals(oldpw)) {
				System.out.println("旧密码输入错误");
				return false;
			}
			// 如果旧密码输入正确，则执行密码修改操作

			// （4）处理执行结果
			String sql2 = "update users set password='" + newpw + "' where id="
					+ id;
			Object[] params2 = {};
			int i = executeUpdate(sql2, params2);
			if (i > 0) {
				System.out.println("密码修改成功！");
			}
			flag = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			this.closeResource();
		}
		return flag;
	}



}
