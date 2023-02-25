package project.demo.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.demo.model.Usuario;
import project.demo.service.IPerfilesService;
import project.demo.service.IUsuariosService;



@Controller
public class HomeController {
	
	@Autowired
	IUsuariosService iserviceUsuario;
    
	@Autowired
	IPerfilesService iservicePerfil;
	
	@Autowired
   	private IUsuariosService serviceUsuarios;
	
	@GetMapping("/")
	public String inicio(Model model,Authentication auth, HttpSession session) {
	

	
				  
		
		  if (auth!=null) {
			  String username = auth.getName(); System.out.println("Nombre del usuario " +
					  username);
			  Usuario usuario = serviceUsuarios.buscarPorUsername(username); 
				 usuario.setPassword(null);
				 model.addAttribute("usuario",usuario);
			   
		  }
		  
		  
				  
			
			/*
			 * if (session.getAttribute("usuario") != null) { Usuario usuario =
			 * serviceUsuarios.buscarPorUsername(username); usuario.setPassword(null);
			 * model.addAttribute("usuario",usuario);
			 * 
			 * }
			 */
			 
		
		return "index";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
		
		
		  String username = auth.getName(); System.out.println("Nombre del usuario " +
		  username);
		  
		  for (GrantedAuthority rol : auth.getAuthorities()) {
		  System.out.println("rol " + rol.getAuthority()); } 
		  
		  if (session.getAttribute("usuario") != null) {
			  Usuario usuario = serviceUsuarios.buscarPorUsername(username); 
			  usuario.setPassword(null);
			  System.out.println("USUARIO "+usuario);
		  session.setAttribute("usuario", usuario); }
		 
		
		
		
		
		return "redirect:/";
		
	}
	
	/*
	 * @GetMapping("/bcrypt/{texto}")
	 * 
	 * @ResponseBody public String encriptar(@PathVariable("texto") String texto) {
	 * return texto +"Encriptado Bcrypt: "+passwordEncoder.encode(texto); }
	 */
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	
	@GetMapping("/logout")
	public String salir(HttpServletRequest request) {
		
		SecurityContextLogoutHandler logoutHandler = 
				new SecurityContextLogoutHandler();
				logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {

		// Ejercicio.
		
		return "redirect:/usuarios/index";
	}
	
	

	
	  @GetMapping("/login") public String login() {
	  
	  
	  return "/login";}
	 

}
