package com.librosV2.Wservice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librosV2.Xrepository.LibroDao;
import com.librosV2.Zmodel.Libro;


@Service("libroService")
@Transactional
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroDao _libroDao;
	
	@Override
	public void guardarLibro(Libro libro) {
		_libroDao.guardarLibro(libro);
		
	}

	@Override
	public void eliminarLibro(int idLibro) {
		
		_libroDao.eliminarLibro(idLibro);
	}

	@Override
	public void actualizarLibro(Libro libro) {
		_libroDao.actualizarLibro(libro);
	}

	@Override
	public List<Libro> buscarTodosLosLibros() {
		return _libroDao.buscarTodosLosLibros();
	}

	@Override
	public Libro encontrarLibroPorId(int idLibro) {
		return _libroDao.encontrarLibroPorId(idLibro);
	}

}
