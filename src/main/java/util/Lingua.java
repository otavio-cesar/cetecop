package util;

public enum Lingua {

	PORTUGUES("Português"), 
	INGLES("Inglês"), 
	ESPANHOL("Espanhol"), 
	FRANCES("Francês"), 
	ITALIANO("Italiano");

	private String nome;

	Lingua(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}