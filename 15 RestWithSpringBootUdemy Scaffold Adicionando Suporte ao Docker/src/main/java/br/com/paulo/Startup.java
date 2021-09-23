package br.com.paulo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.paulo.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
@EnableAutoConfiguration
@ComponentScan
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
//		QUANDO QUISER MUDAR A SENHA É DESSA FORMA
		
		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result  = bCryptPasswordEncoder.encode("admin123");
		System.out.println("Minha Senha Hash "+ result);*/
	}
}
