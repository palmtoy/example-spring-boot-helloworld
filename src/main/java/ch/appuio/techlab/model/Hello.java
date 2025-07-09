package ch.appuio.techlab.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@XmlRootElement
@Table(name = "hello", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Data
public class Hello implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	@NotNull
	@Size(min = 1, max = 255)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy.MM.dd HH:mm:ss", timezone="Asia/Shanghai")
	private Date created;

	private String frontend = System.getenv("HOSTNAME");
	
	public Hello(){
		super();
	}
	
	public Hello(String name, Date created) {
		super();
		this.name = name;
		this.created = created;
	}
}
