package br.com.paulo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.excecao.ExcecaoMatematicaNaoSuportada;

@RestController
public class MatematicaController {
	
//	private static final String template = "Ol�, %s!";
//	private final AtomicLong counter = new AtomicLong();
	
// QUERY PARAM, OS PARAMETROS SÃO PASSADO VIA URL E SÃO OPCIONAIS NA CALCULADORA PODE DAR ERRO SE PASSADO SOMENTE NUMERO EX 
//	@RequestMapping("/greeting") 
//	public Greeting greeting(@RequestParam(value="name", defaultValue = "Mund�o") String name) {
//		return new Greeting(counter.incrementAndGet(), String.format(template, name));
//	}
	
	@RequestMapping(value = "/soma/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
	public Double soma(@PathVariable("numeroUm") String numeroUm, @PathVariable("numeroDois") String numeroDois) throws Exception {
		
		if(!isNumeric(numeroUm)||!isNumeric(numeroDois)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		Double soma = converteParaDouble(numeroUm) + converteParaDouble(numeroDois);
		return soma;
		
	}

	private Double converteParaDouble(String strNumero) {
		
		if(strNumero == null) return 0D;
		
		String numero = strNumero.replaceAll(".", "");
		numero = strNumero.replaceAll(",", ".");
		
		if(isNumeric(numero)) return Double.parseDouble(numero);
		
		return 0D;
	}

	private boolean isNumeric(String strNumero) {
		
		if(strNumero == null) return false;
		
		String numero = strNumero.replaceAll(",", ".");
		//verificao numero
		return numero.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
}
