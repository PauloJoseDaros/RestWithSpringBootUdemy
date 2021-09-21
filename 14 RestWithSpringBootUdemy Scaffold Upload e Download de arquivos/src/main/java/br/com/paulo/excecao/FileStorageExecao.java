package br.com.paulo.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageExecao extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FileStorageExecao(String excecao) {
		super(excecao);
	}
	public FileStorageExecao(String excecao, Throwable causa) {
		super(excecao,causa);
	}
	
}
