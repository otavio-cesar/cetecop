import service.domjudge.CadastrarProblemaService;

public class Teste {
	public static void main(String[] args) {
		CadastrarProblemaService serv = new CadastrarProblemaService();
		serv.guardar(null, null);
	}
}
