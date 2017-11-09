import model.Evento;
import model.entity.Problema;
import model.entity.Versao;
import service.domjudge.CadastroProblemaService;

public class TestaDomjudgeApi {
	public static void main(String[] args) {
		Versao versao = new Versao();
		Evento evento = new Evento();
		Problema problema = new Problema();

		problema.setId(2);

		versao.setNome("nome blalba");
		versao.setProblema(problema);

		evento.setId(2);

		CadastroProblemaService cadastroProblemaservico = new CadastroProblemaService();
		cadastroProblemaservico.guardar(versao, evento);
	}
}
