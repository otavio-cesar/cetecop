package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Solucao;
import repository.SolucaoRepository;
import repository.VersaoRepository;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class RanqueamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolucaoRepository solucaoRepository;

	@Inject
	private VersaoRepository versaoRepository;

	private List<Solucao> solucoes;

	public void inicializar(ComponentSystemEvent e) {
		if (FacesUtil.isNotPostback()) {
			carregarSolucoes();
		}
	}

	public void carregarSolucoes() {
		solucoes = solucaoRepository.solucoesCorrigidas();

		for (Solucao solucao : solucoes) {
			solucao.setVersao(versaoRepository.buscarVersaoPai(solucao.getProblema()));
		}
	}

	public List<Solucao> getSolucoes() {
		return solucoes;
	}

}
