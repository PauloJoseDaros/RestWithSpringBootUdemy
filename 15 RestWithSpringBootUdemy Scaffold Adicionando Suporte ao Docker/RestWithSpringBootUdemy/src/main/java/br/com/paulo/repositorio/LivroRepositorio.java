package br.com.paulo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulo.data.model.Livro;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro,Long>{


}
