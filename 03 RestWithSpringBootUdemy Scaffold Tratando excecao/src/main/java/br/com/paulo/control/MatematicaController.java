package br.com.paulo.control;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.conversores.ConverterNumeros;
import br.com.paulo.excecao.ExcecaoMatematicaNaoSuportada;
import br.com.paulo.matematica.MatematicaSimples;

@RestController
public class MatematicaController {
	
	private MatematicaSimples matematica = new MatematicaSimples();
	
//	private static final String template = "Ol�, %s!";
//	private final AtomicLong counter = new AtomicLong();
	
// QUERY PARAM, OS PARAMETROS SÃO PASSADO VIA URL E SÃO OPCIONAIS NA CALCULADORA PODE DAR ERRO SE PASSADO SOMENTE NUMERO EX 
//	@RequestMapping("/greeting") 
//	public Greeting greeting(@RequestParam(value="name", defaultValue = "Mund�o") String name) {
//		return new Greeting(counter.incrementAndGet(), String.format(template, name));
//	}
	
	@RequestMapping(value = "/soma/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
	public Double soma(@PathVariable("numeroUm") String numeroUm, @PathVariable("numeroDois") String numeroDois) throws Exception {
		
		if(!ConverterNumeros.ehNumerico(numeroUm)||!ConverterNumeros.ehNumerico(numeroDois)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		return matematica.soma(ConverterNumeros.converterDouble(numeroUm), ConverterNumeros.converterDouble(numeroDois));
		
	}

	@RequestMapping(value = "/subtracao/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numeroUm") String numeroUm, @PathVariable("numeroDois") String numeroDois) throws Exception {
		
		if(!ConverterNumeros.ehNumerico(numeroUm)||!ConverterNumeros.ehNumerico(numeroDois)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		return matematica.subtracao(ConverterNumeros.converterDouble(numeroUm), ConverterNumeros.converterDouble(numeroDois));
	}
	
	@RequestMapping(value = "/divisao/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
	public Double div(@PathVariable("numeroUm") String numeroUm, @PathVariable("numeroDois") String numeroDois) throws Exception {
		
		if(!ConverterNumeros.ehNumerico(numeroUm)||!ConverterNumeros.ehNumerico(numeroDois)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		return  matematica.divisao(ConverterNumeros.converterDouble(numeroUm), ConverterNumeros.converterDouble(numeroDois));
	}
	
	@RequestMapping(value = "/media/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
	public Double media(@PathVariable("numeroUm") String numeroUm, @PathVariable("numeroDois") String numeroDois) throws Exception {
		
		if(!ConverterNumeros.ehNumerico(numeroUm)||!ConverterNumeros.ehNumerico(numeroDois)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		return matematica.media(ConverterNumeros.converterDouble(numeroUm), ConverterNumeros.converterDouble(numeroDois));
	}
	@RequestMapping(value = "/multiplicacao/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
	public Double multiplicacao(@PathVariable("numeroUm") String numeroUm, @PathVariable("numeroDois") String numeroDois) throws Exception {
		
		if(!ConverterNumeros.ehNumerico(numeroUm)||!ConverterNumeros.ehNumerico(numeroDois)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		return matematica.multiplicacao(ConverterNumeros.converterDouble(numeroUm), ConverterNumeros.converterDouble(numeroDois));
	}
	@RequestMapping(value = "/raizquadrada/{numeroUm}", method = RequestMethod.GET)
	public Double raiz(@PathVariable("numeroUm") String numeroUm) throws Exception {
		
		if(!ConverterNumeros.ehNumerico(numeroUm)) {
			throw new ExcecaoMatematicaNaoSuportada("Favor passar um valor numérico");
		}
		return matematica.raizQuadrada(ConverterNumeros.converterDouble(numeroUm));
	}
	
}
