package br.com.paulo.control;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags = "EndpointLivros") DIFERENÇA SOMENTE SEM DESCRICAO
//@CrossOrigin
@Api(value = "Endpoint Pessoa", description = "Descrição de PESSOAS aqui ", tags = "EndpointPessoas")
@RestController
@RequestMapping("/api/pessoa/v1")
public class PessoaController {
	
	@Autowired
	private PessoaService peServices;
	
	
	@ApiOperation(value = "pesquisar todas as pessoas")
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public List<PessoaVO> pesquisaTodos()  {
		List<PessoaVO> listPessoasVO = peServices.pesquisaTodos();
		
		listPessoasVO.stream().forEach(p -> p.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(p.getKey())).withSelfRel()));
		return listPessoasVO;
	}
	
	@CrossOrigin(origins = {"http://localhost:8080","http://www.vejaoresultado.com"})// CORS SOMENTE LOCAL HOST e VEJAORESULTADO.COM CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "pesquisar por pessoa")
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public PessoaVO pesquisaPorId(@PathVariable("id") Long id)  {
		
		PessoaVO pessoaVO= peServices.pesquisaPorID(id);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(id)).withSelfRel());
		return pessoaVO;
	}
	
	@CrossOrigin(origins = {"http://localhost:8080","http://www.vejaoresultado.com"})//CORS SOMENTE LOCAL HOST e VEJAORESULTADO.COM CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "salvar nova pessoas")
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public PessoaVO salvar(@RequestBody PessoaVO pPessoaVO)  {
		
		PessoaVO pessoaVO= peServices.salvar(pPessoaVO);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	@CrossOrigin(origins = "http://localhost:8080")// CORS SOMENTE LOCAL HOST CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "Alterar uma pessoa")
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public PessoaVO alterar(@RequestBody PessoaVO pPessoaVO)  {
		PessoaVO pessoaVO=peServices.alterar(pPessoaVO);
		pessoaVO.add(linkTo(methodOn(PessoaController.class).pesquisaPorId(pessoaVO.getKey())).withSelfRel());
		return pessoaVO;
	}
	@CrossOrigin(origins = "http://localhost:8080")//CORS SOMENTE LOCAL HOST CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "Deletar uma pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<PessoaVO> deletar(@PathVariable("id") Long id)  {
		
		 peServices.deletar(id);
		 return ResponseEntity.ok().build();
	}
}
