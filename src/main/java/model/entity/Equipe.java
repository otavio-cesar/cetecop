package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Equipe {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	@Column(columnDefinition = "varchar(1) default 0")
	private boolean isEquipeOneUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isEquipeOneUser() {
		return isEquipeOneUser;
	}

	public void setEquipeOneUser(boolean isEquipeOneUser) {
		this.isEquipeOneUser = isEquipeOneUser;
	}
	
}
