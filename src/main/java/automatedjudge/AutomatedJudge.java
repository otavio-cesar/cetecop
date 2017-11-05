package automatedjudge;

import model.Evento;
import model.entidades.Problema;
import model.entidades.Solucao;
import model.entidades.Versao;

public interface AutomatedJudge {

	public void cadastrarContest(Evento evento);
	
	public void cadastrarProblema(Versao versao, Evento evento);
	
	public void submeterSolucao(Solucao solucao, Problema problema, Evento evento);
}
