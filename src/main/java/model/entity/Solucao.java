package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Solucao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Problema problema;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario user;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] codigoFonte;
	@Column(length = 20)
	private String linguagem;
	@Column(length = 100)
	private String nomeArquivo;
	@Column(nullable = false, columnDefinition = "BIT", length = 1)
	private boolean valida;
	@Column(length = 25)
	private String resultado;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] outputCompile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public byte[] getCodigoFonte() {
		return codigoFonte;
	}

	public void setCodigoFonte(byte[] codigoFonte) {
		this.codigoFonte = codigoFonte;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public boolean isValida() {
		return valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public byte[] getOutputCompile() {
		return outputCompile;
	}

	public void setOutputCompile(byte[] outputCompile) {
		this.outputCompile = outputCompile;
	}

}
