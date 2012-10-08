package dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.User;
@SuppressWarnings({ "rawtypes", "unchecked" })

public class Test {
	
	
	public String[] returnArray(){
		String[] strs=new String[]{"hello","world"};
		return strs;
	}
	
	public List<String> returnList(){
		List<String> list=new ArrayList<String>();
		list.add("hello");
		list.add("world");
		return list;
		
	}
	public Map returnMap(){
		Map map=new HashMap();
		map.put("1", "hello");
		map.put("2", "world");
		return map;
		
	}
	
	public List userList(){
		User u1=new User();
		u1.setUsername("whlweb");
		u1.setPassword("haha");
		User u2=new User();
		u2.setUsername("whlweb2");
		u2.setPassword("haha2");
		
		List list=new ArrayList();
		list.add(u1);
		list.add(u2);
		return list;
		
		
	}
	
	public String varify(User u){
		if(u.getUsername().equals(u.getPassword())){
			return "success!";
		}
		return "failed";
	}

}
