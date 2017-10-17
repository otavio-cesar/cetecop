package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import model.Instituicao;
import model.Usuario;
import repository.InstituicaoRepository;
import repository.UsuarioRepository;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioReposiroty;

	@Inject
	private InstituicaoRepository instituicaoReposiroty;

	private Usuario usuario;
	private Instituicao instituicao;
	private Instituicao instituicaoNova;

	private List<Instituicao> instituicoes;

	public CadastroUsuarioBean() {
		this.usuario = new Usuario();
		this.instituicao = new Instituicao();
		this.instituicaoNova = new Instituicao();
	}

	public void inicializar(ComponentSystemEvent e) {
		System.out.println("inicializando instituicoes");

		if (FacesUtil.isNotPostback()) {
			carregarInstituicoes();
		}
	}

	public void salvar() {
		System.out.println("salvando usuario");
		System.out.println(usuario.toString());

		if (usuarioReposiroty.guardar(usuario)) {
			FacesUtil.addInfoMessage("Usuário cadastrado, faça login.");
			this.usuario = new Usuario();
		} else {
			FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar o usuário.");
		}
	}

	public void salvarInstituicao() {
		System.out.println("salvando instituicao");
		System.out.println(instituicaoNova.toString());
		
		if (instituicaoNova.getNome().trim().equals("")) {
			return;
		}

		if (instituicaoReposiroty.guardar(instituicaoNova)) {
			FacesUtil.addWarnMessage("Instituição cadastrada.");
			carregarInstituicoes();

			this.instituicaoNova = new Instituicao();
		} else {
			FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar instituição.");
		}
	}

	public void carregarInstituicoes() {
		this.instituicoes = instituicaoReposiroty.buscarInstituicao();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Instituicao getInstituicaoNova() {
		return instituicaoNova;
	}

	public void setInstituicaoNova(Instituicao instituicaoNova) {
		this.instituicaoNova = instituicaoNova;
	}

}
