package br.com.paulo.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.paulo.excecao.AutenticacaoJwtInvalidaException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-lenght:3600000}")
	private long validityInMilliseconds = 3600000; // 1h
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostConstruct
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String nome, List<String> roles ) {
		
		Claims claims = Jwts.claims().setSubject(nome);
		claims.put("roles", roles);
		
		Date dateAgora = new Date();
		Date validade = new Date(dateAgora.getTime() + validityInMilliseconds);
		
		
		return Jwts.builder().
				setClaims(claims).
				setIssuedAt(dateAgora).
				setExpiration(validade).
				signWith(SignatureAlgorithm.HS256, secretKey).
				compact();
	}
	
	public Authentication getAuthentication(String token) {
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
	}

	private String getUserName(String token) {
		return Jwts.parser().
				setSigningKey(secretKey).
				parseClaimsJws(token).
				getBody().
				getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		
		String bearerToken = req.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if(claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new AutenticacaoJwtInvalidaException("TOKEN INVALIDO OU EXPIRADO");
		}
		
	}
}
