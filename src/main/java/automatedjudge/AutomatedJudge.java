package automatedjudge;

import java.util.ArrayList;
import model.Evento;
import model.entity.CasoDeTeste;
import model.entity.Equipe;
import model.entity.Solucao;
import model.entity.Versao;

public interface AutomatedJudge {
	
	void cadastrarEvento(Evento evento);
	
	void cadastrarProblema(Versao versao, Evento evento);
	
	void cadastrarEquipe(Equipe equipe);

	void cadastrarCasosDeTeste(ArrayList<CasoDeTeste> casosDeTeste);

	void submeterSolucao(Solucao solucao, Evento evento, Equipe equipe);
	
}
