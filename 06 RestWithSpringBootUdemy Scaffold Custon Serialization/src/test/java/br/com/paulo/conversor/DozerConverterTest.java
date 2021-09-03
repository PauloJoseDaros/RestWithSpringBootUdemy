package br.com.paulo.conversor;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import br.com.paulo.conversor.mocks.MockPessoa;
import br.com.paulo.data.model.Pessoa;
import br.com.paulo.data.vo.v1.PessoaVO;

public class DozerConverterTest {
	
    MockPessoa inputObject= new MockPessoa();

    @Before
    public void setUp() {
        inputObject = new MockPessoa();
    }

    @Test
    public void parseEntityToVOTest() {
        PessoaVO output = DozerConverter.parserObjeto(inputObject.mockEntity(), PessoaVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getIdPessoa());
        Assert.assertEquals("First Name Test0", output.getNome());
        Assert.assertEquals("Last Name Test0", output.getSobrenome());
        Assert.assertEquals("Addres Test0", output.getEndereco());
        Assert.assertEquals("Male", output.getGenero());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PessoaVO> outputList = DozerConverter.parserListaObjeto(inputObject.mockEntityList(), PessoaVO.class);
        PessoaVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getIdPessoa());
        Assert.assertEquals("First Name Test0", outputZero.getNome());
        Assert.assertEquals("Last Name Test0", outputZero.getSobrenome());
        Assert.assertEquals("Addres Test0", outputZero.getEndereco());
        Assert.assertEquals("Male", outputZero.getGenero());
        
        PessoaVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getIdPessoa());
        Assert.assertEquals("First Name Test7", outputSeven.getNome());
        Assert.assertEquals("Last Name Test7", outputSeven.getSobrenome());
        Assert.assertEquals("Addres Test7", outputSeven.getEndereco());
        Assert.assertEquals("Female", outputSeven.getGenero());
        
        PessoaVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getIdPessoa());
        Assert.assertEquals("First Name Test12", outputTwelve.getNome());
        Assert.assertEquals("Last Name Test12", outputTwelve.getSobrenome());
        Assert.assertEquals("Addres Test12", outputTwelve.getEndereco());
        Assert.assertEquals("Male", outputTwelve.getGenero());
    }

    @Test
    public void parseVOToEntityTest() {
        Pessoa output = DozerConverter.parserObjeto(inputObject.mockVO(), Pessoa.class);
        Assert.assertEquals(Long.valueOf(0L), output.getIdPessoa());
        Assert.assertEquals("First Name Test0", output.getNome());
        Assert.assertEquals("Last Name Test0", output.getSobrenome());
        Assert.assertEquals("Addres Test0", output.getEndereco());
        Assert.assertEquals("Male", output.getGenero());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Pessoa> outputList = DozerConverter.parserListaObjeto(inputObject.mockVOList(), Pessoa.class);
        Pessoa outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getIdPessoa());
        Assert.assertEquals("First Name Test0", outputZero.getNome());
        Assert.assertEquals("Last Name Test0", outputZero.getSobrenome());
        Assert.assertEquals("Addres Test0", outputZero.getEndereco());
        Assert.assertEquals("Male", outputZero.getGenero());
        
        Pessoa outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getIdPessoa());
        Assert.assertEquals("First Name Test7", outputSeven.getNome());
        Assert.assertEquals("Last Name Test7", outputSeven.getSobrenome());
        Assert.assertEquals("Addres Test7", outputSeven.getEndereco());
        Assert.assertEquals("Female", outputSeven.getGenero());
        
        Pessoa outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getIdPessoa());
        Assert.assertEquals("First Name Test12", outputTwelve.getNome());
        Assert.assertEquals("Last Name Test12", outputTwelve.getSobrenome());
        Assert.assertEquals("Addres Test12", outputTwelve.getEndereco());
        Assert.assertEquals("Male", outputTwelve.getGenero());
    }
}
