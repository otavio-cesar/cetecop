package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.CasoDeTeste;
import model.Categoria;
import model.Problema;
import model.ProblemaHasCategoria;
import model.Versao;
import repository.CasoDeTesteRepository;
import repository.CategoriaRepository;
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

	private Problema problema;
	private Versao versao;
	private Lingua lingua;
	private Categoria categoriaNova;
	private List<Categoria> selectedCategorias;
	private List<Categoria> categorias;
	private List<Lingua> linguas;
	private List<CasoDeTeste> casoDeTestes;
	private CasoDeTeste casoDeTesteNovo;

	public CadastroProblemaBean() {
		this.problema = new Problema();
		this.versao = new Versao();
		this.categoriaNova = new Categoria();
		this.casoDeTesteNovo = new CasoDeTeste();
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

		if (problema.getId() == null) {
			FacesUtil
					.addErrorMessage("Ocorreu um erro ao cadastar o problema.");
			return;
		}

		versao.setProblema(problema);

		if (versaoRepository.guardar(versao)) {
			for (int i = 0; i < selectedCategorias.size(); i++) {
				ProblemaHasCategoria problemaCategoria = new ProblemaHasCategoria();
				problemaCategoria.setProblema(problema);

				Categoria categoria = categoriaRepository.buscarPorId(Integer
						.parseInt(selectedCategorias.get(i) + ""));
				problemaCategoria.setCategoria(categoria);
				problemaCategoria.setUser(problema.getOwner());

				if (!problemaCategoriaRepository.guardar(problemaCategoria)) {
					FacesUtil
							.addErrorMessage("Ocorreu um erro ao cadastar a categoria do problema.");
					return;
				}
			}
		} else {
			FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar a versao.");
			return;
		}

		// if(casoTesteRepository.guardar(casoTeste)){
		// TODO
		// }else{
		// FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar o caso de teste.");
		// return;
		// }

		FacesUtil.addInfoMessage("Problema cadastrado com sucesso.");
	}

	public void salvarCategoria() {
		categoriaNova.setUser(Seguranca.getUsuarioLogado().getUsuario());

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

	public void salvarCasoDeTeste() {
		System.out.println("adad");
		casoDeTesteNovo.setCreator(Seguranca.getUsuarioLogado().getUsuario());

		// if (casoDeTesteRepository.guardar(casoDeTesteNovo)) {
		// FacesUtil.addWarnMessage("Caso de Teste cadastrado.");
		// carregarCategorias();

		// this.casoDeTesteNovo = new CasoDeTeste();
		// } else {
		// //FacesUtil.addErrorMessage("Ocorreu um erro ao cadastar o caso de teste.");
		// }
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

	public List<CasoDeTeste> getCasoDeTestes() {
		return casoDeTestes;
	}

	public void setCasoDeTestes(List<CasoDeTeste> casoDeTestes) {
		this.casoDeTestes = casoDeTestes;
	}

	public CasoDeTeste getCasoDeTesteNovo() {
		return casoDeTesteNovo;
	}

	public void setCasoDeTesteNovo(CasoDeTeste casoDeTesteNovo) {
		this.casoDeTesteNovo = casoDeTesteNovo;
	}

}
