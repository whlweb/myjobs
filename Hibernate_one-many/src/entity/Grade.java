package entity;

import java.util.HashSet;
import java.util.Set;

public class Grade {
	private int gid;
	private String name;
	private String g_desc;
	private Set<Student> students=new HashSet<Student>();

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getG_desc() {
		return g_desc;
	}

	public void setG_desc(String g_desc) {
		this.g_desc = g_desc;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
