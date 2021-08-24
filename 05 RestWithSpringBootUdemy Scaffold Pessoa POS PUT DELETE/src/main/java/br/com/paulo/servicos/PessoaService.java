package br.com.paulo.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.paulo.model.Pessoa;

@Service
public class PessoaService {
	
	private final AtomicLong counter = new AtomicLong();
	
	public Pessoa salvar(Pessoa pPessoa) {
		return pPessoa;
	}
	
	public Pessoa alterar(Pessoa pPessoa) {
		return pPessoa;
	}
	
	public void deletar(String id) {
		
	}
	
	public Pessoa pesquisaPorID(String id) {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setIdPessoa(counter.incrementAndGet());
		pessoa.setNome("Paulo");
		pessoa.setSobrenome("Daros");
		pessoa.setEndereco("Rua das palmeiras numero 500");
		pessoa.setGenero("Masculino");
		
		return pessoa;
	}
	
	public List<Pessoa> pesquisaTodos() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (int i = 0; i < 8; i++) {
			Pessoa pessoa = mockPessoa(i);
			pessoas.add(pessoa);
		}
		return pessoas;
	}

	private Pessoa mockPessoa(int i) {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setIdPessoa(counter.incrementAndGet());
		pessoa.setNome("Primeiro Nome"+i);
		pessoa.setSobrenome("Sobre nome"+i);
		pessoa.setEndereco("Endereço no brasil"+i);
		pessoa.setGenero("Genero"+i);
		
		return pessoa;
	}
	
}
