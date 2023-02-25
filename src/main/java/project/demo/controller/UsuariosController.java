package project.demo.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.demo.model.Usuario;
import project.demo.service.IPerfilesService;
import project.demo.service.IUsuariosService;
import project.demo.service.db.UsuariosServiceJpa;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	IUsuariosService iserviceUsuario;
    
	@Autowired
	IPerfilesService iservicePerfil;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @GetMapping("/listaUsuarios")
	public String mostrarIndex(Model model) {
			
			List<Usuario> listaUsuarios = new LinkedList<Usuario>();
			listaUsuarios= iserviceUsuario.buscarTodos();
			if(listaUsuarios.size()>0) {
				
				model.addAttribute("usuarios", listaUsuarios);

				
			}else {
				model.addAttribute("msg", "No hay ningún usuario registrado.");

			}
		//	model.addAttribute("cursos", serviceCursos.buscarTodos());    	
    	return "usuarios/listadoUsuarios";
	}
    
	@GetMapping("/create")
	public String crearUsuario(Usuario usuario) {
		return "usuarios/formUsuarios";}
	
	
	@PostMapping("/save")
	public String crearUsuario(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			
			}			
			return "usuarios/formUsuarios";
			
			
		}
		
	
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

	iserviceUsuario.guardar(usuario);
	
	attributes.addFlashAttribute("msgregistro", "Registro Guardado");
		
	return "redirect:/usuarios/listaUsuarios";   
		
		
		}
    
	
	@GetMapping("/Editar/{id}")
	public String EditarUsuario(@PathVariable("id") int idUsuario,RedirectAttributes attributes,Model model) {

		Usuario usuario = iserviceUsuario.buscarPorId(idUsuario);	
		model.addAttribute("usuario",usuario);
		
		//System.out.println("Registro: "+ curso.getNombre());
		return "usuarios/formUsuarios";   
		}
	
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	try {
    		iserviceUsuario.eliminar(idUsuario);
    		attributes.addFlashAttribute("msgborrado", "Registro Borrado");
		} catch (Exception e) {
			attributes.addFlashAttribute("msgborrado", "Error al borrar registro");
		}
    	
    	
    	
    	
    	
    	
    	    	
		return "redirect:/usuarios/listaUsuarios";
	}
    
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("perfiles", iservicePerfil.buscarTodos());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	//	webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
}
