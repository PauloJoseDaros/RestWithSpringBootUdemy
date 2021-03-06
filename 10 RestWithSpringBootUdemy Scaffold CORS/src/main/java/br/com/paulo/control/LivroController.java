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

import br.com.paulo.data.vo.v1.LivroVO;
import br.com.paulo.servicos.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Api(tags = "EndpointLivros") DIFERENÇA SOMENTE SEM DESCRICAO
@Api(value = "Endpoint Livro", description = "Descrição de Livros aqui ", tags = "EndpointLivros")
@RestController
@RequestMapping("/api/livro/v1")
public class LivroController {
	
	@Autowired
	private LivroService peServices;
	
	@CrossOrigin(origins = "http://localhost:8080")// CORS SOMENTE LOCAL HOST CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "pesquisar todos os livros")
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public List<LivroVO> pesquisaTodos()  {
		List<LivroVO> listLivrosVO = peServices.pesquisaTodos();
		
		listLivrosVO.stream().forEach(p -> p.add(linkTo(methodOn(LivroController.class).pesquisaPorId(p.getKey())).withSelfRel()));
		return listLivrosVO;
	}
	@CrossOrigin(origins = {"http://localhost:8080","https://www.vejaoresultado.com"})// CORS SOMENTE LOCAL HOST e VEJAORESULTADO.COM CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "pesquisar por livro")
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public LivroVO pesquisaPorId(@PathVariable("id") Long id)  {
		
		LivroVO livroVO= peServices.pesquisaPorID(id);
		livroVO.add(linkTo(methodOn(LivroController.class).pesquisaPorId(id)).withSelfRel());
		return livroVO;
	}
	@CrossOrigin(origins = {"http://localhost:8080","https://www.vejaoresultado.com"})// CORS SOMENTE LOCAL HOST e VEJAORESULTADO.COM CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "Salvar um novo livro")
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public LivroVO salvar(@RequestBody LivroVO pLivroVO)  {
		
		LivroVO livroVO= peServices.salvar(pLivroVO);
		livroVO.add(linkTo(methodOn(LivroController.class).pesquisaPorId(livroVO.getKey())).withSelfRel());
		return livroVO;
	}
	@CrossOrigin(origins = "http://localhost:8080")// CORS SOMENTE LOCAL HOST CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "Alterar um livro")
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
			consumes = {"application/json","application/xml","application/x-yaml"})
	public LivroVO alterar(@RequestBody LivroVO pLivroVO)  {
		LivroVO livroVO=peServices.alterar(pLivroVO);
		livroVO.add(linkTo(methodOn(LivroController.class).pesquisaPorId(livroVO.getKey())).withSelfRel());
		return livroVO;
	}
	@CrossOrigin(origins = "http://localhost:8080")// CORS SOMENTE LOCAL HOST CONSEGUE ACESSAR ESSE ENDPOINT
	@ApiOperation(value = "Excluir um livro")
	@DeleteMapping("/{id}")
	public ResponseEntity<LivroVO> deletar(@PathVariable("id") Long id)  {
		
		 peServices.deletar(id);
		 return ResponseEntity.ok().build();
	}
}
