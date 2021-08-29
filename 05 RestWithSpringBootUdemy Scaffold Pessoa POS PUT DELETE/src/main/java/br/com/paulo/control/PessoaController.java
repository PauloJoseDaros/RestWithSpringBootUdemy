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

import br.com.paulo.data.vo.PessoaVO;
import br.com.paulo.servicos.PessoaService;


@RestController
@RequestMapping("/Pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService peServices;
	
	@GetMapping
	public List<PessoaVO> pesquisaTodos()  {
		
		return peServices.pesquisaTodos();
	}
	
	@GetMapping("/{id}")
	public PessoaVO pesquisaPorId(@PathVariable("id") Long id)  {
		
		return peServices.pesquisaPorID(id);
	}
	
	@PostMapping
	public PessoaVO salvar(@RequestBody PessoaVO pPessoaVO)  {
		
		return peServices.salvar(pPessoaVO);
	}
	
	@PutMapping
	public PessoaVO alterar(@RequestBody PessoaVO pPessoaVO)  {
		
		return peServices.alterar(pPessoaVO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PessoaVO> deletar(@PathVariable("id") Long id)  {
		
		 peServices.deletar(id);
		 return ResponseEntity.ok().build();
	}
}
