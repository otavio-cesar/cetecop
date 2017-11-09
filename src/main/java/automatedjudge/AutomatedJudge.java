package automatedjudge;

import model.Evento;
import model.entity.Problema;
import model.entity.Solucao;
import model.entity.Versao;

public interface AutomatedJudge {

	public void cadastrarContest(Evento evento);
	
	public void cadastrarProblema(Versao versao, Evento evento);
	
	public void submeterSolucao(Solucao solucao, Problema problema, Evento evento);
}
