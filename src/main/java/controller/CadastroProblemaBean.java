package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.Categoria;
import model.Lingua;
import model.Problema;
import model.Versao;
import repository.CategoriaRepository;
import repository.ProblemaRepository;
import security.Seguranca;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProblemaRepository problemaRepository;

	@Inject
	private CategoriaRepository categoriaRepository;

	private Problema problema;
	private Versao versao;
	private Lingua lingua;
	private Categoria categoriaNova;
	private List<Categoria> selectedCategorias;
	private List<Categoria> categorias;

	public CadastroProblemaBean() {
		this.problema = new Problema();
		this.versao = new Versao();
		this.lingua = new Lingua();
		this.categoriaNova = new Categoria();
	}

	public void inicializar(ComponentSystemEvent e) {
		System.out.println("inicializando categorias");

		if (FacesUtil.isNotPostback()) {
			carregarCategorias();
		}
	}

	private void carregarCategorias() {
		this.categorias = categoriaRepository.buscarCategoria();
	}

	public void salvar() {
		System.out.println("salvando problema");
		System.out.println(problema.toString());

		if (problemaRepository.guardar(problema)) {
			FacesUtil.addInfoMessage("Problema cadastrado com sucesso.");
			this.problema = new Problema();
		} else {
			FacesUtil
					.addErrorMessage("Ocorreu um erro ao cadastar o problema.");
		}
	}

	public void salvarCategoria() {
		categoriaNova.setUser(Seguranca.getUsuarioLogado().getUsuario());
		
		System.out.println("salvando categoria");
		System.out.println(categoriaNova.toString());

		if (categoriaNova.getNome().trim().equals("")) {
			return;
		}

		if (categoriaRepository.guardar(categoriaNova)) {
			FacesUtil.addWarnMessage("Categoria cadastrada.");
			carregarCategorias();

			this.categoriaNova = new Categoria();
		} else {
			FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar categoria.");
		}
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

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

}
