package project.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, estatus from usuarios where username=? ")
				.authoritiesByUsernameQuery("select u.username, p.nombre from usuariosperfiles up "
						+ "inner join usuarios u on u.idusuario = up.idusuario "
						+ "inner join perfiles p on p.idPerfil = up.idPerfil " + "where u.username = ?");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// Los recursos estáticos no requieren auteyonticación
		.antMatchers("/css/**","/datetimepicker/**","/js/**","/fonts/**", "/images/**", "/tinymce/**").permitAll()
		// Las vistas públicas no requieren autenticación
		.antMatchers("/","/bcrypt/**").permitAll()
		
		

		
		// Asignar permisos a URLs por ROLES
		
		.antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
		

		
		
		
		
	
		// Todas las demás URLs de la Aplicación requieren autenticación
		/*
		 * .anyRequest().authenticated() .and() .oauth2Login()
		 */
		
		// El formulario de Login no requiere autenticacion
		.and().formLogin().loginPage("/login").permitAll();
	

	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
