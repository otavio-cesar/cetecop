package security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}

		return nome;
	}

	public static UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}

	public boolean isSubmissaoProblemaPermitido() {
		return getUsuarioLogado() == null ? false : true;
	}
	

	public boolean isCadastroProblemaPermitido() {
		return externalContext.isUserInRole("PROFESSOR");
	}

	public boolean isControleVersaoPermitido() {
		return externalContext.isUserInRole("PROFESSOR");
	}

	public boolean isUsuarioLogado() {
		return getUsuarioLogado() == null ? false : true;
	}

}
