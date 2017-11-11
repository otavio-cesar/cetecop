package automatedjudge.domjudge;

import automatedjudge.AutomatedJudge;
import automatedjudge.domjudge.service.CadastroProblemaService;
import model.Evento;
import model.entity.Problema;
import model.entity.Solucao;
import model.entity.Versao;

public class DomjudgeApi implements AutomatedJudge {

	@Override
	public void cadastrarContest(Evento evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarProblema(Versao versao, Evento evento) {
		CadastroProblemaService cadastrarProblema = new CadastroProblemaService();
		cadastrarProblema.guardar(versao, evento);

	}

	@Override
	public void submeterSolucao(Solucao solucao, Problema problema, Evento evento) {
		// TODO Auto-generated method stub

	}

}
