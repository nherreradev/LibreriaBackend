package com.librosV2.Xrepository;

import java.util.List;

import com.librosV2.Zmodel.Libro;



public interface LibroDao {
	
void guardarLibro(Libro libro);
	
	void eliminarLibro(int idLibro);
	
	void actualizarLibro(Libro libro);
	
	List<Libro> buscarTodosLosLibros();
	
	Libro encontrarLibroPorId(int idLibro);

}
