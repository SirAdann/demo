package project.demo.service;

import java.util.List;

import project.demo.model.Perfil;

public interface IPerfilesService {

	List<Perfil> buscarTodos();
	void borrarPerfil(Integer idPerfil);
	void guardarPerfil(Perfil perfil);
	Perfil buscarPorId(Integer idPerfil);
}
