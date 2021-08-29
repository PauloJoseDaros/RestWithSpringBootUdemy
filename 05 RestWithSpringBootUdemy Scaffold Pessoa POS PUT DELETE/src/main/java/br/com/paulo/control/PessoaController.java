package br.com.paulo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.model.Pessoa;
import br.com.paulo.servicos.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService peServices;
	
	@GetMapping
	public List<Pessoa> pesquisaTodos()  {
		
		return peServices.pesquisaTodos();
	}
	
	@GetMapping("/{id}")
	public Pessoa pesquisaPorId(@PathVariable("id") Long id)  {
		
		return peServices.pesquisaPorID(id);
	}
	
	@PostMapping
	public Pessoa salvar(@RequestBody Pessoa pPessoa)  {
		
		return peServices.salvar(pPessoa);
	}
	
	@PutMapping
	public Pessoa alterar(@RequestBody Pessoa pPessoa)  {
		
		return peServices.alterar(pPessoa);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> deletar(@PathVariable("id") Long id)  {
		
		 peServices.deletar(id);
		 return ResponseEntity.ok().build();
	}
}
