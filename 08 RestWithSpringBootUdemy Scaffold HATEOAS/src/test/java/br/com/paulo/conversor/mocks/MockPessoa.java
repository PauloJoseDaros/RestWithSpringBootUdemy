package br.com.paulo.conversor.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.paulo.data.model.Pessoa;
import br.com.paulo.data.vo.v1.PessoaVO;


public class MockPessoa {


    public Pessoa mockEntity() {
    	return mockEntity(0);
    }
    
    public PessoaVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Pessoa> mockEntityList() {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        for (int i = 0; i < 14; i++) {
        	pessoas.add(mockEntity(i));
        }
        return pessoas;
    }

    public List<PessoaVO> mockVOList() {
        List<PessoaVO> pessoas = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
        	pessoas.add(mockVO(i));
        }
        return pessoas;
    }
    
    private Pessoa mockEntity(Integer number) {
    	Pessoa pessoa = new Pessoa();
    	pessoa.setEndereco("Addres Test" + number);
    	pessoa.setNome("First Name Test" + number);
    	pessoa.setGenero(((number % 2)==0) ? "Male" : "Female");
    	pessoa.setIdPessoa(number.longValue());
    	pessoa.setSobrenome("Last Name Test" + number);
        return pessoa;
    }

    private PessoaVO mockVO(Integer number) {
    	PessoaVO pessoa = new PessoaVO();
    	pessoa.setEndereco("Addres Test" + number);
    	pessoa.setNome("First Name Test" + number);
    	pessoa.setGenero(((number % 2)==0) ? "Male" : "Female");
    	pessoa.setIdPessoa(number.longValue());
    	pessoa.setSobrenome("Last Name Test" + number);
        return pessoa;
    }

}
