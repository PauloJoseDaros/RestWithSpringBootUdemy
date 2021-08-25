package br.com.paulo.excecao.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.paulo.excecao.ExcecaoResponse;
import br.com.paulo.excecao.RecursoNaoEncontrado;

@ControllerAdvice
@RestController
public class ManipuladorExcecaoResponsePersonalizada extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExcecaoResponse> manipularTodasException(Exception ex,WebRequest request){
		ExcecaoResponse excecaoResponse = new ExcecaoResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(excecaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(RecursoNaoEncontrado.class)
	public final ResponseEntity<ExcecaoResponse> manipularBadException(Exception ex,WebRequest request){
		ExcecaoResponse excecaoResponse = new ExcecaoResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(excecaoResponse, HttpStatus.BAD_REQUEST);
	}
}
