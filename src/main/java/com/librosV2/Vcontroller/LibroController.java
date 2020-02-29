package com.librosV2.Vcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.librosV2.Wservice.LibroService;
import com.librosV2.Zmodel.Libro;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class LibroController {
	
	@Autowired
	private LibroService _libroService;
	
	// OBTENER TODOS LOS LIBROS
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = "/libros", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<List<Libro>> listaDeLibros() {

			List<Libro> listaDeLibros = _libroService.buscarTodosLosLibros();

			

			return new ResponseEntity<List<Libro>>(listaDeLibros, HttpStatus.ACCEPTED);

		}
		
		
		
		
		
		// OBTENER LIBRO ESPECIFICO POR ID
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value = "/libros/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Libro> encontrarLibroPorId(@PathVariable("id") int idLibro) {

			

			Libro libro = _libroService.encontrarLibroPorId(idLibro);

			

			return new ResponseEntity<Libro>(libro, HttpStatus.OK);

		}
		
		
		
		
		
		// CREAR LIBRO
		
		@RequestMapping(value = "/crearLibro", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<String> crearLibro(@RequestBody  Libro libro, UriComponentsBuilder uriComponentsBuilder) {

			

			_libroService.guardarLibro(libro);

			Libro libroRecienGuardadoRecuperado = _libroService.encontrarLibroPorId(libro.getIdLibro());

			HttpHeaders headers = new HttpHeaders();

			headers.setLocation(uriComponentsBuilder.path("/crearLibro/{id}")
					.buildAndExpand(libroRecienGuardadoRecuperado.getIdLibro()).toUri());

			return new ResponseEntity<String>(HttpStatus.OK);
		}
		
		
		
		
		
		
		
		
		
		
		
		

}
