package br.com.paulo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.paulo.repositorio.UserRepositorio;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepositorio repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repositorio.pesquisaPorNomeUsuario(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("Nome " + username + " não encontrado");
		}
	}

	

}
