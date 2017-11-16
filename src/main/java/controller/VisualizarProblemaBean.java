package controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Versao;
import repository.VersaoRepository;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class VisualizarProblemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VersaoRepository versaoRepository;
	
	private Versao versao;
	
	public VisualizarProblemaBean() {
	}
	
	public void inicializar(ComponentSystemEvent e) {
		System.out.println(versao);
		if (FacesUtil.isNotPostback()) {
		}
	}
	
	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

}
