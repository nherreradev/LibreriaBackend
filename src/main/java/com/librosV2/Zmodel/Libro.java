package com.librosV2.Zmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="libro")
public class Libro {
	
	
	@Id
	@Column(name="idLibro")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idLibro;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="autor")
	private String autor;
	
	@Column(name="imagenLibro")
	private String imagenLibro;

	public Libro(Integer idLibro, String nombre, String autor, String imagenLibro) {
		super();
		this.idLibro = idLibro;
		this.nombre = nombre;
		this.autor = autor;
		this.imagenLibro = imagenLibro;
	}
	
	
	
	

	public Libro() {
		
	}





	public Integer getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getImagenLibro() {
		return imagenLibro;
	}

	public void setImagenLibro(String imagenLibro) {
		this.imagenLibro = imagenLibro;
	}
	
	
	
	
	
	
	
	

}