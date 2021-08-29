package br.com.paulo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulo.data.model.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa,Long>{


}
