package automatedjudge.domjudge;

import automatedjudge.AutomatedJudge;
import model.Evento;
import model.entity.Problema;
import model.entity.Solucao;
import model.entity.Versao;
import service.domjudge.CadastroProblemaService;

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
