package project.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfiles")
public class Perfil {
	
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idPerfil;
private String nombre;


public Integer getIdperfil() {
	return idPerfil;
}
public void setIdperfil(Integer idperfil) {
	this.idPerfil = idperfil;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Integer getIdPerfil() {
	return idPerfil;
}
public void setIdPerfil(Integer idPerfil) {
	this.idPerfil = idPerfil;
}

@Override
public String toString() {
	return "Perfil [idPerfil=" + idPerfil + ", nombre=" + nombre + ", detalles="  + "]";
}



}
