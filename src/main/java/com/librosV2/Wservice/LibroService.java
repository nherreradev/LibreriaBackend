package com.librosV2.Wservice;

import java.util.List;

import com.librosV2.Zmodel.Libro;

public interface LibroService {
	
	
	void guardarLibro(Libro libro);
	
	void eliminarLibro(int idLibro);
	
	void actualizarLibro(Libro libro);
	
	List<Libro> buscarTodosLosLibros();
	
	Libro encontrarLibroPorId(int idLibro);

}
