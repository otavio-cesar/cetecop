package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Categoria;
import model.entity.Versao;
import repository.CategoriaRepository;
import repository.VersaoRepository;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VersaoRepository versaoRepository;

	@Inject
	private CategoriaRepository categoriaRepository;

	private List<Versao> versoes;

	public PesquisarProblemaBean() {
		versoes = new ArrayList<Versao>();
	}

	public void inicializar(ComponentSystemEvent e) {
		if (FacesUtil.isNotPostback()) {
			carregarProblemas();
		}
	}

	private void carregarProblemas() {
		versoes = versaoRepository.buscarVersaoPai();

		for (Versao versao : versoes) {
			List<Categoria> categorias = categoriaRepository.buscarCategorias(versao.getProblema());
			String categoriasString = "";
			
			for (Categoria categoria : categorias) {
				categoriasString += categoria.getNome() + ", ";
			}
			
			if (categoriasString.length() >= 2)
				categoriasString = categoriasString.substring(0, categoriasString.length() - 2);
			versao.getProblema().setCategorias(categoriasString);
		}
	}

	public List<Versao> getVersoes() {
		return versoes;
	}

}
