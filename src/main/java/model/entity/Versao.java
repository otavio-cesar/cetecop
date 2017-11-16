package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import util.Lingua;

@Entity
public class Versao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Lingua lingua;

	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(nullable = false)
	private Problema problema;

	private boolean isVersaoPai;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(nullable = false)
	private Usuario user;

	@NotNull
	@Column(length = 1000)
	private String enunciado;

	@Column(length = 240)
	private String tematica;

	@NotNull
	@Column(length = 100)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Lingua getLingua() {
		return lingua;
	}

	public void setLingua(Lingua lingua) {
		this.lingua = lingua;
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Versao [id=" + id + ", lingua=" + lingua + ", problema="
				+ problema + ", user=" + user + ", enunciado=" + enunciado
				+ ", tematica=" + tematica + ", nome=" + nome + "]";
	}

	public boolean isVersaoPai() {
		return isVersaoPai;
	}

	public void setVersaoPai(boolean isVersaoPai) {
		this.isVersaoPai = isVersaoPai;
	}

}
