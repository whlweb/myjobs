package test;

import java.util.HashMap;
import java.util.Map;

import set.User3;
import dao.User3DAO;

public class Test3 {
	
	public static void main(String[] args) {
		User3 user = new User3();
		user.setUsername("user1");
		Map images = new HashMap();
		images.put("pic1", "C:/");
		images.put("pic2", "D:/");
		images.put("pic3", "D:/");
		user.setImages(images);
		User3DAO user3Dao=new User3DAO();
		user3Dao.save(user);
		user3Dao.printUser(user3Dao.loadUser(1));
	}

}
