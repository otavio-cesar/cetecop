import java.util.ArrayList;

import automatedjudge.domjudge.service.CadastroCasoDeTesteService;
import automatedjudge.domjudge.service.CadastroProblemaService;
import model.Evento;
import model.entity.CasoDeTeste;
import model.entity.Problema;
import model.entity.Versao;

public class TestaDomjudgeApi {
	public static void main(String[] args) {
		Versao versao = new Versao();
		Evento evento = new Evento();
		Problema problema = new Problema();
		ArrayList<CasoDeTeste> casosDeTeste = new ArrayList<>();
		CasoDeTeste casoDeTeste;
		
		problema.setId(5);

		versao.setNome("nome blalba");
		versao.setProblema(problema);

		evento.setId(2);

		CadastroProblemaService cadastroProblemaServico = new CadastroProblemaService();
		cadastroProblemaServico.guardar(versao, evento);
		
		casoDeTeste = new CasoDeTeste();
		casoDeTeste.setDescricao("descricao");
		casoDeTeste.setProblema(problema);
		casoDeTeste.setId(4);
		casosDeTeste.add(casoDeTeste);
		
		casoDeTeste = new CasoDeTeste();
		casoDeTeste.setDescricao("descricao 2");
		casoDeTeste.setProblema(problema);
		casoDeTeste.setId(3);
		casosDeTeste.add(casoDeTeste);
		
		CadastroCasoDeTesteService cadastroCasoDeTesteServico = new CadastroCasoDeTesteService();
		cadastroCasoDeTesteServico.guardar(casosDeTeste);
	}
}
