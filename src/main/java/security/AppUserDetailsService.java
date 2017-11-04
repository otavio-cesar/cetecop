package security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import repository.UsuarioRepository;
import util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioRepository usuarioRepository = CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = usuarioRepository.buscarPorEmail(email);
		User user = null;
			
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(usuario.getTipo().toString().toUpperCase()));
		
		return authorities;
	}

}
