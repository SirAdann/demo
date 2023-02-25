package project.demo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import project.demo.model.Usuario;

import project.demo.repository.UsuariosRepository;
import project.demo.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	UsuariosRepository usuariosRepo;
	
	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub 
		usuariosRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
usuariosRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorId(int idUsuario) {
Optional<Usuario> optional= usuariosRepo.findById(idUsuario);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		return usuariosRepo.findByUsername(username);
	}

}
