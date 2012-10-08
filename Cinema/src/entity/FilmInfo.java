package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class FilmInfo {

	private long fid;
	private String name;
	private FilmType filmtype;
	private String actor;
	private String director;
	private double price;

	@GenericGenerator(name = "gen", strategy = "increment")
	@Id
	@GeneratedValue(generator = "gen")
	@Column(name = "FID", nullable = false, precision = 8, scale = 0)
	@BatchSize(size = 20)
	@Fetch(value = FetchMode.JOIN)
	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = FilmType.class,fetch=FetchType.EAGER)
	public FilmType getFilmtype() {
		return filmtype;
	}

	public void setFilmtype(FilmType filmtype) {
		this.filmtype = filmtype;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
