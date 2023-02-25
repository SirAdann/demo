package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.demo.model.Perfil;


public interface PerfilesRepository extends JpaRepository<Perfil, Integer> {

}
