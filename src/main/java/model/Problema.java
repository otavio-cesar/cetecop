package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Problema {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Usuario owner;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Problema_has_Taxonomia")
	private List<Taxonomia> taxonomia;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Problema_has_Categoria")
	private List<Taxonomia> categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public List<Taxonomia> getTaxonomia() {
		return taxonomia;
	}

	public void setTaxonomia(ArrayList<Taxonomia> taxonomia) {
		this.taxonomia = taxonomia;
	}

	public List<Taxonomia> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Taxonomia> categoria) {
		this.categoria = categoria;
	}

}
