package automatedjudge.domjudge;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import automatedjudge.AutomatedJudge;
import automatedjudge.domjudge.service.CadastroCasoDeTesteService;
import automatedjudge.domjudge.service.CadastroEquipeService;
import automatedjudge.domjudge.service.CadastroProblemaService;
import automatedjudge.domjudge.service.CadastroSolucaoService;
import model.Evento;
import model.entity.CasoDeTeste;
import model.entity.Equipe;
import model.entity.Solucao;
import model.entity.Versao;

public class DomjudgeApi implements AutomatedJudge {

	@Override
	public void cadastrarEvento(Evento evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarProblema(Versao versao, Evento evento) {
		CadastroProblemaService cadastroProblemaServico = new CadastroProblemaService();
		cadastroProblemaServico.guardar(versao, evento);
	}

	@Override
	public void cadastrarCasosDeTeste(List<CasoDeTeste> casosDeTeste) {
		CadastroCasoDeTesteService cadastroCasoDeTesteServico = new CadastroCasoDeTesteService();
		try {
			cadastroCasoDeTesteServico.guardar(casosDeTeste);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void submeterSolucao(Solucao solucao, Evento evento, Equipe equipe) {
		CadastroSolucaoService cadastroSolucaoService = new CadastroSolucaoService();
		cadastroSolucaoService.guardar(solucao, evento, equipe);
	}

	@Override
	public void cadastrarEquipe(Equipe equipe) {
		CadastroEquipeService cadastroEquipeServico = new CadastroEquipeService();
		cadastroEquipeServico.guardar(equipe);
	}

	@Override
	public Evento buscaEventoComumATodos() {
		// TODO Procura no domjudge um evento com nome unico, se nao achar, cadastra
		// evento com nome unico e retorna seu id para ser usado por todos usuarios.

		Evento evento = new Evento();

		evento.setNome("chave unica para representar id");
		evento.setId(2);

		return evento;
	}

}
