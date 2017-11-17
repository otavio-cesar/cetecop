package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import automatedjudge.AutomatedJudge;
import model.entity.Solucao;
import model.entity.Versao;
import repository.EquipeRepository;
import repository.SolucaoRepository;
import security.Seguranca;
import util.Linguagem;
import util.cdi.Domjudge;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class VisualizarProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@Domjudge
	private AutomatedJudge automatedJudge;

	@Inject
	private SolucaoRepository solucaoRepository;

	@Inject
	private EquipeRepository equipeRepository;

	private Versao versao;

	private List<Linguagem> linguagens;

	private String codigoFonte;

	private Solucao solucao;

	public VisualizarProblemaBean() {
		solucao = new Solucao();
		solucao.setCodigoFonte(getMainTemplate());
		linguagens = new ArrayList<Linguagem>();
	}

	public void inicializar(ComponentSystemEvent e) {
		if (FacesUtil.isNotPostback()) {
			carregarLinguagem();
		}
	}

	private void carregarLinguagem() {
		this.linguagens = Arrays.asList(Linguagem.values());
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public void submeterSolucao() {
		solucao.setNomeArquivo(versao.getNome().replace(" ", "") + "." + solucao.getLinguagem());
		solucao.setUser(Seguranca.getUsuarioLogado().getUsuario());
		solucao.setProblema(versao.getProblema());

		solucao = solucaoRepository.guardar(solucao);

		automatedJudge.submeterSolucao(solucao, automatedJudge.buscaEventoComumATodos(),
				equipeRepository.buscarEquipeUnica(Seguranca.getUsuarioLogado().getUsuario()));

		FacesUtil.addInfoMessage("Solução submetida com sucesso. Acompanhe o resultado pelo ranqueamento.");

		carregarLinguagem();
		solucao = new Solucao();
		solucao.setCodigoFonte(getMainTemplate());
	}

	public String getMainTemplate() {
		return "class Main\n" + "{\n	public static void main(String[] args)\n"
				+ "	{\n		// Escreva seu código aqui\n	}\n}";
	}

	public List<Linguagem> getLinguagens() {
		return linguagens;
	}

	public void setLinguagens(List<Linguagem> linguagens) {
		this.linguagens = linguagens;
	}

	public String getCodigoFonte() {
		return codigoFonte;
	}

	public void setCodigoFonte(String codigoFonte) {
		this.codigoFonte = codigoFonte;
	}

	public Solucao getSolucao() {
		return solucao;
	}

	public void setSolucao(Solucao solucao) {
		this.solucao = solucao;
	}
}
