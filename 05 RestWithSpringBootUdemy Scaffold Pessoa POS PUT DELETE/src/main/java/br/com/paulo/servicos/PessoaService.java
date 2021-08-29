package br.com.paulo.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo.data.model.Pessoa;
import br.com.paulo.excecao.RecursoNaoEncontrado;
import br.com.paulo.repositorio.PessoaRepositorio;

@Service
public class PessoaService {

	@Autowired
	PessoaRepositorio repositorio;

	public Pessoa salvar(Pessoa pPessoa) {
		repositorio.save(pPessoa);
		return pPessoa;
	}

	public Pessoa alterar(Pessoa pPessoa) {
		
		Pessoa pessoa = repositorio.findById(pPessoa.getIdPessoa()).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));

		pessoa.setNome(pPessoa.getNome());
		pessoa.setSobrenome(pPessoa.getSobrenome());
		pessoa.setEndereco(pPessoa.getEndereco());
		pessoa.setGenero(pPessoa.getGenero());
		
		return repositorio.save(pessoa); 
	}

	public void deletar(Long id) {
		Pessoa pessoa = repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
		
		repositorio.delete(pessoa);
	}

	public Pessoa pesquisaPorID(Long id) {
		
		return repositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Não encontramos nenhum registro para esse ID"));
	}

	public List<Pessoa> pesquisaTodos() {
		return repositorio.findAll();
	}

}
