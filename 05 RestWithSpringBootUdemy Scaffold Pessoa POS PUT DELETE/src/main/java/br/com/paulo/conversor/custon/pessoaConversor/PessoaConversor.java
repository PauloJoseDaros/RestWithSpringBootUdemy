package br.com.paulo.conversor.custon.pessoaConversor;

import java.util.Date;

import br.com.paulo.data.model.Pessoa;
import br.com.paulo.data.vo.v2.PessoaVOV2;

public class PessoaConversor {
	
	public PessoaVOV2 converterEntidadeParaVO(Pessoa pessoa) {
		
		PessoaVOV2 vo = new PessoaVOV2();
		
		vo.setIdPessoa(pessoa.getIdPessoa());
		vo.setEndereco(pessoa.getEndereco());
		vo.setGenero(pessoa.getGenero());
		vo.setNome(pessoa.getNome());
		vo.setDataNascimento(new Date());
		vo.setSobrenome(pessoa.getSobrenome());
		return vo;
	}
	
	public Pessoa converterVOparaEntidade(PessoaVOV2 pessoaVOV2) {
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setIdPessoa(pessoaVOV2.getIdPessoa());
		pessoa.setEndereco(pessoaVOV2.getEndereco());
		pessoa.setGenero(pessoaVOV2.getGenero());
		pessoa.setNome(pessoaVOV2.getNome());
		pessoa.setSobrenome(pessoaVOV2.getSobrenome());
		return pessoa;
	}

}
