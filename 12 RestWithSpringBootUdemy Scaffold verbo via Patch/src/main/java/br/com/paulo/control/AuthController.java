package br.com.paulo.control;

import static org.springframework.http.ResponseEntity.ok;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.repositorio.UserRepositorio;
import br.com.paulo.security.CredencialContaVO;
import br.com.paulo.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoint Autenticacao", description = "Nome e senha ", tags = "EndpointAutenticacao")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepositorio repositorio;
	
	
	@ApiOperation(value = "Autenticar usuario usando as credenciais")
	@PostMapping(value = "/signin", produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity salvar(@RequestBody CredencialContaVO pDados)  {
		
		try {
			
			var username = pDados.getNomeUsuario();
			var password = pDados.getSenha();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repositorio.pesquisaPorNomeUsuario(username);
			
			var token = "";
			
			if(user!=null) {
				token = tokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Usuario "+username + " Não encontrado!");
			}
			Map<Object,Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Nome / Senha invalidos");
		}
		
	}
}
