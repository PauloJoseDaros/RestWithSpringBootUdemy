package br.com.paulo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.paulo.data.model.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa,Long>{

	@Modifying
	@Query("UPDATE Pessoa p SET p.ativo = false WHERE p.idPessoa =:id")
	void desabilitarPessoa(@Param("id") Long id);

}
