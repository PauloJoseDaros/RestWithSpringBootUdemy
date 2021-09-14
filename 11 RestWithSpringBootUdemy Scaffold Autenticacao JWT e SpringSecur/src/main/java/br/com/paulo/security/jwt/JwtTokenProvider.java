package br.com.paulo.security.jwt;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {

	@Value 
	private String secretKey = "secret";
	
	private long validityInMilliseconds = 3600000; // 1h
}
