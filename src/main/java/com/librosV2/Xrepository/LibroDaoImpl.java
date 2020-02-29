package com.librosV2.Xrepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.librosV2.Xrepository.AbstractSession;
import com.librosV2.Xrepository.LibroDao;
import com.librosV2.Zmodel.Libro;

@Repository
@Transactional
public class LibroDaoImpl extends AbstractSession implements LibroDao {

	@Override
	public void guardarLibro(Libro libro) {
		
		obtenerSession().save(libro);
	}

	@Override
	public void eliminarLibro(int idLibro) {
		
		Libro curso = (Libro)obtenerSession().get(Libro.class, idLibro);
		 
		 if(curso != null){
		 obtenerSession().delete(curso);
		 }
		
	}

	@Override
	public void actualizarLibro(Libro libro) {
		obtenerSession().update(libro);
	}

	@Override
	public List<Libro> buscarTodosLosLibros() {
		
		List<Libro> listaDeCursos = obtenerSession().createQuery("from Libro").list();
		
		
		
		return listaDeCursos;
	}

	@Override
	public Libro encontrarLibroPorId(int idLibro) {
		return (Libro)obtenerSession().get(Libro.class, idLibro);
	}
	
	

}
