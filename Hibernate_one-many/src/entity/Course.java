package entity;

import java.util.HashSet;
import java.util.Set;

public class Course {
	private int cid;
	private String name;
	private Set<Student> students=new HashSet<Student>();
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
