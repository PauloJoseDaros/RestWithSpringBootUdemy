package br.com.paulo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.paulo.conversor.DozerConverter;
import br.com.paulo.conversor.custon.pessoaConversor.PessoaConversor;
import br.com.paulo.data.model.Pessoa;
import br.com.paulo.data.vo.v1.PessoaVO;
import br.com.paulo.excecao.RecursoNaoEncontrado;
import br.com.paulo.repositorio.PessoaRepositorio;

@Service
public class PessoaService {

	@Autowired
	PessoaRepositorio repositorio;

	public PessoaVO salvar(PessoaVO pPessoaVO){
		
		var entidade = DozerConverter.parserObjeto(pPessoaVO, Pessoa.class);
		var vo = DozerConverter.parserObjeto(repositorio.save(entidade), PessoaVO.class);
		return vo;
	}
	
	public PessoaVO alterar(PessoaVO pPessoaVO) {
		
		var entidade = repositorio.findById(pPessoaVO.getKey()).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		entidade.setNome(pPessoaVO.getNome());
		entidade.setSobrenome(pPessoaVO.getSobrenome());
		entidade.setEndereco(pPessoaVO.getEndereco());
		entidade.setGenero(pPessoaVO.getGenero());
		var vo = DozerConverter.parserObjeto(entidade, PessoaVO.class);
		
		return vo; 
	}
	
	@Transactional 
	public PessoaVO desabilitarPessoa(Long id) {
		repositorio.desabilitarPessoa(id);
		var entidade = repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		
		return DozerConverter.parserObjeto(entidade, PessoaVO.class);
	}


	public void deletar(Long id) {
		
		Pessoa pessoa = repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		
		repositorio.delete(pessoa);
	}

	public PessoaVO pesquisaPorID(Long id) {
		
		var entidade = repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		
		return DozerConverter.parserObjeto(entidade, PessoaVO.class);
	}

	public List<PessoaVO> pesquisaTodos(Pageable pageable) {
		
		var entities = repositorio.findAll(pageable).getContent();
		
		return DozerConverter.parserListaObjeto(entities, PessoaVO.class);
	}

}
