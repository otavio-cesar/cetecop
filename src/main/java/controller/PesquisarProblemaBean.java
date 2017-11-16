package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Versao;
import repository.VersaoRepository;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VersaoRepository versaoRepository;
	
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
		
	}

	public List<Versao> getVersoes() {
		return versoes;
	}
}
