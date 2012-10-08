package annotations;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "WEAPON", schema = "HIBERNATE")
@NamedQuery(name = "namedQuery", query = "from Weapon where wid<:wid")

@SqlResultSetMapping(name="nativeQueryMapping", 
entities={ 
    @EntityResult(entityClass=annotations.Weapon.class, fields={
        @FieldResult(name="wid", column="wid"),
        @FieldResult(name="name", column="name"), 
     })}
)
@NamedNativeQuery(name = "nativeQuery", query = "select * from weapon where wid>:wid",resultSetMapping="nativeQueryMapping")
public class Weapon {
	private long wid;
	private String name;

	@GenericGenerator(name = "gen", strategy="increment")
	@Id
	@GeneratedValue(generator="gen")
	@Column(name="WID",nullable=false,precision=8,scale=0)
	public long getWid() {
		return wid;
	}

	public void setWid(long wid) {
		this.wid = wid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
