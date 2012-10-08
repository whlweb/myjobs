package test;

import java.util.HashSet;
import java.util.Set;

import set.User;

import dao.UserDAO;

public class Test {
	
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("user3");
		Set<String> images = new HashSet<String>();
		images.add("pic1");
		images.add("pic2");
		images.add("pic3");
		images.add("pic4");
		images.add("pic5");
		user.setImages(images);
		UserDAO userDao=new UserDAO();
		userDao.save(user);
		userDao.printUser(userDao.loadUser(3));
	}

}
