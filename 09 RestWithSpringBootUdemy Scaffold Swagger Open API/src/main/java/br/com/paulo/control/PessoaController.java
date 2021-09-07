package br.com.paulo.control;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import br.com.paulo.data.vo.v1.PessoaVO;
import br.com.paulo.servicos.PessoaService;


@RestController
@RequestMapping("/api/pessoa/v1")
public class PessoaController {
	
	@Autowired
	private PessoaService peServices;
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public List<PessoaVO> pesquisaTodos()  {
		List<PessoaVO> listPessoasVO = peServices.pesquisaTodos();
		
		listPessoasVO.stream().forEach(p -> p.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(p.getKey())).withSelfRel()));
		return listPessoasVO;
		
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public PessoaVO pesquisaPorId(@PathVariable("id") Long id)  {
		
		PessoaVO pessoaVO= peServices.pesquisaPorID(id);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(id)).withSelfRel());
		return pessoaVO;
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public PessoaVO salvar(@RequestBody PessoaVO pPessoaVO)  {
		
		PessoaVO pessoaVO= peServices.salvar(pPessoaVO);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public PessoaVO alterar(@RequestBody PessoaVO pPessoaVO)  {
		PessoaVO pessoaVO=peServices.alterar(pPessoaVO);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PessoaVO> deletar(@PathVariable("id") Long id)  {
		
		 peServices.deletar(id);
		 return ResponseEntity.ok().build();
	}
}
