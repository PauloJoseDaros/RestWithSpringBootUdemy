package br.com.paulo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.paulo.data.model.User;

@Repository
public interface UserRepositorio extends JpaRepository<User,Long>{
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User pesquisaPorNomeUsuario(@Param("userName")String userName);
	
}
