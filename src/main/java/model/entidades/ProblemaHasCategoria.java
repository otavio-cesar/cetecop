package model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "problema_has_categoria")
public class ProblemaHasCategoria {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Problema problema;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario user;

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
