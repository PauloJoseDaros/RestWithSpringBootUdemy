package br.com.paulo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.model.Pessoa;
import br.com.paulo.servicos.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService peServices;
	
	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Pessoa> pesquisaTodos()  {
		
		return peServices.pesquisaTodos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public Pessoa pesquisaPorId(@PathVariable("id") String id)  {
		
		return peServices.pesquisaPorID(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes =   MediaType.APPLICATION_JSON_VALUE)
	public Pessoa salvar(@RequestBody Pessoa pPessoa)  {
		
		return peServices.salvar(pPessoa);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes =   MediaType.APPLICATION_JSON_VALUE)
	public Pessoa alterar(@RequestBody Pessoa pPessoa)  {
		
		return peServices.alterar(pPessoa);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletar(@PathVariable("id") String id)  {
		
		 peServices.deletar(id);
	}
}
