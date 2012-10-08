package service.impl;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import service.UserService;

import dao.UserDao;

import entity.User;

public class UserServiceImpl implements UserService  {
	private UserDao ud;

	@Override
	public User login(String username, String password) {
		return ud.login(username, password);
	}
	
	@Override
	public void logout(HttpServletRequest req){
		ud.logout(req);
	}


	@Override
	public List<User> getUserList() {
		return ud.getUserList();
	}

	@Override
	public List<User> query(String q_name) {
		return ud.query(q_name);
	}

	@Override
	public boolean add(User user) {
		return ud.add(user);
	}

	@Override
	public boolean delete(String id) {
		return ud.delete(id);
	}

	@Override
	public boolean update(User user, String id) {
		return ud.update(user, id);
	}

	@Override
	public boolean changepw(String id, String oldpw, String newpw) {
		return ud.changepw(id, oldpw, newpw);
	}

	public UserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	
	

	
}
