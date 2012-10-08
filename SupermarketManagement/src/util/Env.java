package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("serial")
public class Env extends Properties {
	private static Env instance;
	private Env() {
		InputStream is = getClass().getResourceAsStream("db.properties");
		try {
			load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Env getInstance(){
		if (instance!=null){
			return instance;
		}else{
			makeInstance();
			return instance;
		}
	}
	private synchronized static void makeInstance() {
		if(instance==null){
			instance=new Env();
		}
		
	}

}
