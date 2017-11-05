package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import model.entidades.CasoDeTeste;
import model.entidades.Categoria;
import model.entidades.Limite;
import model.entidades.Problema;
import model.entidades.ProblemaHasCategoria;
import model.entidades.Versao;
import repository.CasoDeTesteRepository;
import repository.CategoriaRepository;
import repository.LimiteRepository;
import repository.ProblemaHasCategoriaRepository;
import repository.ProblemaRepository;
import repository.VersaoRepository;
import security.Seguranca;
import util.jsf.FacesUtil;
import util.jsf.Lingua;

@Named
@ViewScoped
public class CadastroProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VersaoRepository versaoRepository;

	@Inject
	private CategoriaRepository categoriaRepository;

	@Inject
	private ProblemaHasCategoriaRepository problemaCategoriaRepository;

	@Inject
	private ProblemaRepository problemaRepository;

	@Inject
	private CasoDeTesteRepository casoDeTesteRepository;

	@Inject
	private LimiteRepository limiteRepository;

	private Problema problema;
	private Versao versao;
	private Lingua lingua;
	private Categoria categoriaNova;
	private List<Categoria> selectedCategorias;
	private Categoria selectedCategoriaToLimite;
	private List<Categoria> categorias;
	private List<Lingua> linguas;
	private List<CasoDeTeste> casosDeTeste;
	private CasoDeTeste casoDeTesteNovo;
	private CasoDeTeste selectedCasoDeTeste;
	private Limite selectedLimite;
	private Limite limite;
	private List<Limite> limites;

	public CadastroProblemaBean() {
		this.problema = new Problema();
		this.versao = new Versao();
		this.categoriaNova = new Categoria();
		this.casoDeTesteNovo = new CasoDeTeste();
		this.casosDeTeste = new ArrayList<CasoDeTeste>();
		this.limite = new Limite();
		this.limites = new ArrayList<Limite>();

	}

	public void inicializar(ComponentSystemEvent e) {
		if (FacesUtil.isNotPostback()) {
			carregarCategorias();
			carregarLinguas();
		}
	}

	private void carregarLinguas() {
		this.linguas = Arrays.asList(Lingua.values());
	}

	private void carregarCategorias() {
		this.categorias = categoriaRepository.buscarCategoria();
	}

	public void salvar() {
		versao.setLingua(lingua);
		versao.setUser(Seguranca.getUsuarioLogado().getUsuario());

		problema.setOwner(Seguranca.getUsuarioLogado().getUsuario());
		problema = problemaRepository.guardar(problema);

		versao.setProblema(problema);

		versaoRepository.guardar(versao);

		// Cadastro de categorias

		for (int i = 0; i < selectedCategorias.size(); i++) {
			ProblemaHasCategoria problemaCategoria = new ProblemaHasCategoria();
			problemaCategoria.setProblema(problema);

			Integer categoriaId = Integer.parseInt(selectedCategorias.get(i) + "");
			Categoria categoria = categoriaRepository.buscarPorId(categoriaId);
			problemaCategoria.setCategoria(categoria);
			problemaCategoria.setUser(problema.getOwner());

			problemaCategoriaRepository.guardar(problemaCategoria);
		}

		// Cadastro de casos de teste

		for (int i = 0; i < casosDeTeste.size(); i++) {
			casosDeTeste.get(i).setProblema(problema);
			casoDeTesteRepository.guardar(casosDeTeste.get(i));
		}

		// Cadastro de limites

		for (int i = 0; i < limites.size(); i++) {
			limites.get(i).setProblema(problema);
			limiteRepository.guardar(limites.get(i));
		}

		this.casosDeTeste = new ArrayList<CasoDeTeste>();
		this.versao = new Versao();
		this.problema = new Problema();
		this.selectedCategorias = new ArrayList<Categoria>();
		this.limites = new ArrayList<Limite>();
		this.limite = new Limite();

		FacesUtil.addInfoMessage("Problema cadastrado com sucesso.");
	}

	public void salvarCategoria() {
		categoriaNova.setUser(Seguranca.getUsuarioLogado().getUsuario());

		if (categoriaNova.getNome().trim().equals("")) {
			return;
		}

		categoriaRepository.guardar(categoriaNova);
		FacesUtil.addWarnMessage("Categoria cadastrada.");
		carregarCategorias();

		this.categoriaNova = new Categoria();
	}

	public void excluirCasoDeTeste() {
		casosDeTeste.remove(selectedCasoDeTeste);
		FacesUtil.addWarnMessage("Caso de teste removido.");
	}

	public void excluirLimite() {
		limites.remove(selectedLimite);
		FacesUtil.addWarnMessage("Limite removido.");
	}

	public void salvarCasoDeTeste() {
		casoDeTesteNovo.setCreator(Seguranca.getUsuarioLogado().getUsuario());
		casoDeTesteNovo.setPadrao(true);

		casosDeTeste.add(casoDeTesteNovo);
		casoDeTesteNovo = new CasoDeTeste();

		FacesUtil.addWarnMessage("Caso de teste adicionado.");
	}

	public void salvarLimite() {
		limite.setCategoria(selectedCategoriaToLimite);
		limites.add(limite);

		limite = new Limite();
		selectedCategoriaToLimite = null;

		FacesUtil.addWarnMessage("Limite adicionado.");
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema usuario) {
		this.problema = usuario;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public Lingua getLingua() {
		return lingua;
	}

	public void setLingua(Lingua lingua) {
		this.lingua = lingua;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public List<Categoria> getSelectedCategorias() {
		return selectedCategorias;
	}

	public void setSelectedCategorias(List<Categoria> selectedCategorias) {
		this.selectedCategorias = selectedCategorias;
	}

	public Categoria getCategoriaNova() {
		return categoriaNova;
	}

	public void setCategoriaNova(Categoria categoriaNova) {
		this.categoriaNova = categoriaNova;
	}

	public List<Lingua> getLinguas() {
		return linguas;
	}

	public List<CasoDeTeste> getCasosDeTeste() {
		return casosDeTeste;
	}

	public void setCasoDeTesteNovo(CasoDeTeste casoDeTesteNovo) {
		this.casoDeTesteNovo = casoDeTesteNovo;
	}

	public CasoDeTeste getSelectedCasoDeTeste() {
		return selectedCasoDeTeste;
	}

	public void setSelectedCasoDeTeste(CasoDeTeste selectedCasoDeTeste) {
		this.selectedCasoDeTeste = selectedCasoDeTeste;
	}

	public CasoDeTeste getCasoDeTesteNovo() {
		return casoDeTesteNovo;
	}

	public Limite getLimite() {
		return limite;
	}

	public void setLimite(Limite limite) {
		this.limite = limite;
	}

	public List<Limite> getLimites() {
		return limites;
	}

	public Limite getSelectedLimite() {
		return selectedLimite;
	}

	public void setSelectedLimite(Limite selectedLimite) {
		this.selectedLimite = selectedLimite;
	}

	public Categoria getSelectedCategoriaToLimite() {
		return selectedCategoriaToLimite;
	}

	public void setSelectedCategoriaToLimite(Categoria selectedCategoriaToLimite) {
		this.selectedCategoriaToLimite = selectedCategoriaToLimite;
	}

}
