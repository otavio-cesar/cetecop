import java.util.ArrayList;
import java.util.Random;
import automatedjudge.domjudge.DomjudgeApi;
import model.Evento;
import model.entity.CasoDeTeste;
import model.entity.Equipe;
import model.entity.Problema;
import model.entity.Solucao;
import model.entity.Versao;
import repository.EquipeRepository;
import util.Linguagem;

public class TestaDomjudgeApi {
	
	public static void main(String[] args) {
		DomjudgeApi domjudgeApi = new DomjudgeApi();
		
		Random rnd = new Random();
		Versao versao = new Versao();
		Evento evento = new Evento();
		Problema problema = new Problema();
		ArrayList<CasoDeTeste> casosDeTeste = new ArrayList<>();
		CasoDeTeste casoDeTeste;
		
		problema.setId(rnd.nextInt(9999));

		versao.setNome("nome blalba");
		versao.setProblema(problema);

		evento.setId(2);

		domjudgeApi.cadastrarProblema(versao, evento);
		
		casoDeTeste = new CasoDeTeste();
		casoDeTeste.setDescricao("descricao");
		casoDeTeste.setProblema(problema);
		casoDeTeste.setEntrada("1");
		casoDeTeste.setSaida("Hello World!");
		casoDeTeste.setId(4);
		casosDeTeste.add(casoDeTeste);
		
		casoDeTeste = new CasoDeTeste();
		casoDeTeste.setDescricao("descricao 2");
		casoDeTeste.setProblema(problema);
		casoDeTeste.setEntrada("1");
		casoDeTeste.setSaida("Hello World!");
		casoDeTeste.setId(3);
		//casosDeTeste.add(casoDeTeste);
		
		domjudgeApi.cadastrarCasosDeTeste(casosDeTeste);
		
		Solucao solucao = new Solucao();
		solucao.setId(rnd.nextInt());
		solucao.setCodigoFonte(helloworld());
		solucao.setLinguagem(Linguagem.java);
		solucao.setNomeArquivo("nomedoarquivo.java");
		solucao.setProblema(problema);
		
		Equipe equipe = new Equipe();
		equipe.setId(rnd.nextInt());
		equipe.setNome("nome equipe");
		equipe.setEquipeOneUser(true);
		
		equipe = new EquipeRepository().guardar(equipe);
		
		domjudgeApi.cadastrarEquipe(equipe);
		
		domjudgeApi.submeterSolucao(solucao, evento, equipe);
	}

	private static String helloworld() {
		return "import java.io.*;\n" + 
				"\n" + 
				"class Main\n" + 
				"{\n" + 
				"	public static BufferedReader in;\n" + 
				"\n" + 
				"	public static void main(String[] args) throws IOException\n" + 
				"	{\n" + 
				"		in = new BufferedReader(new InputStreamReader(System.in));\n" + 
				"\n" + 
				"		int nTests = Integer.parseInt(in.readLine());\n" + 
				"\n" + 
				"		for (int i = 0; i < nTests; i++) {\n" + 
				"			String name = in.readLine();\n" + 
				"			System.out.println(\"Hello World!\");\n" + 
				"		}\n" + 
				"	}\n" + 
				"}";
	}
	
}
