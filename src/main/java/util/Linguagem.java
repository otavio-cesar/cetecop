package util;

public enum Linguagem {

	java("Java"), 
	c("C"), 
	cpp("C++"), 
	csharp("C#"), 
	p2("Pyton 2"),
	rb("Ruby");

	private String nome;

	Linguagem(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}