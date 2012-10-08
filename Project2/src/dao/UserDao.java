package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import entity.User;

public interface UserDao {

	public User login(String username, String password);

	public void logout(HttpServletRequest request);

	public List<User> getUserList();

	// 查询
	public List<User> query(String q_name);

	// 增加用户信息
	public boolean add(User user);

	// 删除用户信息
	public boolean delete(String id);

	// 修改用户
	public boolean update(User user, String id);

	public boolean changepw(String id, String oldpw, String newpw);

	public int getTotalCount();

	public List<User> getByPage(int pageNo, int pageSize);
}
