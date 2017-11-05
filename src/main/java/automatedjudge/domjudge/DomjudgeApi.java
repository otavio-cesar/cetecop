package automatedjudge.domjudge;

import automatedjudge.AutomatedJudge;
import model.Evento;
import model.entidades.Problema;
import model.entidades.Solucao;
import model.entidades.Versao;
import service.domjudge.CadastrarProblemaService;

public class DomjudgeApi implements AutomatedJudge {

	@Override
	public void cadastrarContest(Evento evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarProblema(Versao versao, Evento evento) {
		CadastrarProblemaService cadastrarProblema = new CadastrarProblemaService();
		cadastrarProblema.guardar(versao, evento);

	}

	@Override
	public void submeterSolucao(Solucao solucao, Problema problema, Evento evento) {
		// TODO Auto-generated method stub

	}

}
