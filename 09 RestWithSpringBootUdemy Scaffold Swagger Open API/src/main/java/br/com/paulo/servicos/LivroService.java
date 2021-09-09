package br.com.paulo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo.conversor.DozerConverter;
import br.com.paulo.data.model.Livro;
import br.com.paulo.data.vo.v1.LivroVO;
import br.com.paulo.excecao.RecursoNaoEncontrado;
import br.com.paulo.repositorio.LivroRepositorio;

@Service
public class LivroService {

	@Autowired
	LivroRepositorio repositorio;

	public LivroVO salvar(LivroVO pLivroVO){
		
		var entidade = DozerConverter.parserObjeto(pLivroVO, Livro.class);
		var vo = DozerConverter.parserObjeto(repositorio.save(entidade), LivroVO.class);
		return vo;
	}
	
	public LivroVO alterar(LivroVO pLivroVO) {
		
		var entidade = repositorio.findById(pLivroVO.getKey()).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		entidade.setAutor(pLivroVO.getAutor());
		entidade.setDataLancamento(pLivroVO.getDataLancamento());
		entidade.setPreco(pLivroVO.getPreco());
		entidade.setTitulo(pLivroVO.getTitulo());
		var vo = DozerConverter.parserObjeto(entidade, LivroVO.class);
		
		return vo; 
	}

	public void deletar(Long id) {
		
		Livro livro = repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		
		repositorio.delete(livro);
	}

	public LivroVO pesquisaPorID(Long id) {
		
		var entidade = repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		
		return DozerConverter.parserObjeto(entidade, LivroVO.class);
	}

	public List<LivroVO> pesquisaTodos() {
		return DozerConverter.parserListaObjeto(repositorio.findAll(), LivroVO.class);
	}

}
