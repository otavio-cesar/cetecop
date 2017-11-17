package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario_Has_Equipe")
public class UsuarioHasEquipe {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Equipe equipe;

	public Usuario getUsuario() {
		return usuario;
	}
	
	public UsuarioHasEquipe() {
		
	}

	public UsuarioHasEquipe(Usuario usuario, Equipe equipe) {
		this.usuario = usuario;
		this.equipe = equipe;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
