package project.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.demo.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	
	
	//select * from Usuarios where estatus =1
	List<Usuario> findByEstatus(Integer estatus);
	Usuario findByUsername(String username);
	
}
