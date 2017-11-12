package automatedjudge.domjudge;

import java.util.ArrayList;

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
	public void cadastrarCasosDeTeste(ArrayList<CasoDeTeste> casosDeTeste) {
		CadastroCasoDeTesteService cadastroCasoDeTesteServico = new CadastroCasoDeTesteService();
		cadastroCasoDeTesteServico.guardar(casosDeTeste);
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

}
