package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import automatedjudge.AutomatedJudge;
import model.entity.Equipe;
import model.entity.Instituicao;
import model.entity.Usuario;
import model.entity.UsuarioHasEquipe;
import repository.EquipeRepository;
import repository.InstituicaoRepository;
import repository.UsuarioHasEquipeRepository;
import repository.UsuarioRepository;
import util.cdi.Domjudge;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	@Domjudge
	private AutomatedJudge automatedjudge;

	@Inject
	private InstituicaoRepository instituicaoRepository;

	@Inject
	private EquipeRepository equipeRepository;
	
	@Inject
	private UsuarioHasEquipeRepository usuarioHasEquipeRepository;

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
		if (FacesUtil.isNotPostback()) {
			carregarInstituicoes();
		}
	}

	public void salvar() {
		usuario = usuarioRepository.guardar(usuario);

		Equipe equipe  = equipeRepository.guardar(new Equipe(usuario.getEmail(), true));
		
		usuarioHasEquipeRepository.guardar(new UsuarioHasEquipe(usuario, equipe));
		
		automatedjudge.cadastrarEquipe(equipe);

		FacesUtil.addInfoMessage("Usuário cadastrado, faça login.");

		this.usuario = new Usuario();
	}

	public void salvarInstituicao() {
		if (instituicaoNova.getNome().trim().equals("")) {
			return;
		}

		if (instituicaoRepository.guardar(instituicaoNova)) {
			FacesUtil.addWarnMessage("Instituição cadastrada.");
			carregarInstituicoes();

			this.instituicaoNova = new Instituicao();
		} else {
			FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar instituição.");
		}
	}

	public void carregarInstituicoes() {
		this.instituicoes = instituicaoRepository.buscarInstituicao();
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
