package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class FilmType {
	private long tid;
	private String type;

	@GenericGenerator(name = "typeGen", strategy = "increment")
	@Id
	@GeneratedValue(generator = "typeGen")
	@Column(name = "TID", nullable = false, precision = 8, scale = 0)
	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
