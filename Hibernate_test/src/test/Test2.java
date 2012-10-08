package test;

import java.util.List;
import java.util.Vector;

import set.User2;
import dao.User2DAO;

public class Test2 {
	
	public static void main(String[] args) {
		User2 user = new User2();
		user.setUsername("user1");
		List<String> images = new Vector<String>();
		images.add("pic1");
		images.add("pic2");
		images.add("pic3");
		images.add("pic4");
		images.add("pic5");
		user.setImages(images);
		User2DAO user2Dao=new User2DAO();
		user2Dao.save(user);
		user2Dao.printUser(user2Dao.loadUser(2));
	}

}
