package com.librosV2.Vcontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.librosV2.Wservice.LibroService;
import com.librosV2.Zmodel.Libro;
import com.librosV2.util.ValidarNumero;



@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class LibroController {
	
	@Autowired
	private LibroService _libroService;
	
	
	// ---------------------- CREATE ------------------------------------------------------------------------------------------------------
	
			// CREAR LIBRO - Camb111io
			@CrossOrigin(origins = "http://localhost:4200")
			@RequestMapping(value = "/crearLibro", method = RequestMethod.POST, headers = "Accept=application/json")
			public ResponseEntity<String> crearLibro(@RequestBody  Libro libro, UriComponentsBuilder uriComponentsBuilder) {

				
				if (libro.getNombre().isEmpty() || libro.getAutor().isEmpty()){
					Libro libro2 = new Libro();
					Libro libro3 = new Libro();
					return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
				}

				_libroService.guardarLibro(libro);

				Libro libroRecienGuardadoRecuperado = _libroService.encontrarLibroPorId(libro.getIdLibro());

				HttpHeaders headers = new HttpHeaders();

				headers.setLocation(uriComponentsBuilder.path("/crearLibro/{id}")
						.buildAndExpand(libroRecienGuardadoRecuperado.getIdLibro()).toUri());

				return new ResponseEntity<String>(HttpStatus.OK);
			}
			
			
			
			
			
			
			
			
			//SUBIR IMAGEN LIBRO
			private static final String RUTA_IMAGENES = "C:/Users/nherr/Documents/backupParaSsd/primeraAplicacionConAngular/src/assets/imagenes/";
			private static final String RUTA_RELATIVA_LEVANTA_VSCODE = "../../assets/imagenes/";
			@CrossOrigin(origins = "http://localhost:4200")
			@RequestMapping(value = "/libro/imagen", method = RequestMethod.POST, headers = "Accept=application/json")
			public ResponseEntity<byte[]> subirImagenLibro(@RequestParam("idLibro") String idLibro, @RequestParam("imagen") MultipartFile archivo){
				
				
				Integer idLibroAInteger = Integer.valueOf(idLibro);
				
				Libro libroEncontrado = _libroService.encontrarLibroPorId(idLibroAInteger);
				
				
				String nombreDelArchivoCompleto =  libroEncontrado.getNombre()+ "-" + libroEncontrado.getAutor() + "."+ archivo.getContentType().split("/")[1];
				
				libroEncontrado.setImagenLibro(RUTA_RELATIVA_LEVANTA_VSCODE + nombreDelArchivoCompleto);
				
				try {
					
					byte[] bytes = archivo.getBytes();
					
					Path ruta = Paths.get(RUTA_IMAGENES + nombreDelArchivoCompleto);
					
					Files.write(ruta, bytes);
					
					_libroService.actualizarLibro(libroEncontrado);
					
					return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
					
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return new ResponseEntity(HttpStatus.CONFLICT);
				
			}
	
			
	// --------------------------------------------------------------------------------------------------------------------------------
			
			
			
			
			
			
   // ---------------------- READ ------------------------------------------------------------------------------------------------------	
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
		public ResponseEntity<Libro> encontrarLibroPorId(@PathVariable("id") String idLibro) {

			if (ValidarNumero.isNumeric(idLibro)) {
				
				Integer integerConseguido = Integer.parseInt(idLibro);

				Libro libro = _libroService.encontrarLibroPorId(integerConseguido);
				
				return new ResponseEntity<Libro>(libro, HttpStatus.OK);
			
			}
			
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			

			

		}
		
		
		
		
		// --------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		//------------------------UPDATE---------------------------------------------------------------------------------------------------
		
		//ACTUALIZAR LIBRO
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value="/libro/actualizar/{id}", method = RequestMethod.PATCH, headers="Accept=application/json")
		public ResponseEntity<Libro> actualizarLibro(@RequestBody Libro libro, @PathVariable("id") String idLibro){
			
			
			Integer idLibroInteger = Integer.valueOf(idLibro);
			
			Libro libroEncontrado = _libroService.encontrarLibroPorId(idLibroInteger);
			
			libroEncontrado.setNombre(libro.getNombre());
			libroEncontrado.setAutor(libro.getAutor());
			libroEncontrado.setImagenLibro(libroEncontrado.getImagenLibro());
			
			
			
			_libroService.actualizarLibro(libroEncontrado);
			
			return new ResponseEntity<Libro>(libroEncontrado ,HttpStatus.OK);
			
			
		}
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
		//------------------------DELETE---------------------------------------------------------------------------------------------------
		//ElIMINAR LIBRO
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value="/libro/eliminar/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
		public ResponseEntity<Libro> eliminarLibro(@PathVariable("id") Integer idLibro){
			
			
			
			Libro libroEncontradoPorIdAEliminar = _libroService.encontrarLibroPorId(idLibro);
			
			
			
			
			_libroService.eliminarLibro(idLibro);
			
			return new ResponseEntity<Libro>(libroEncontradoPorIdAEliminar, HttpStatus.OK);
			
			
		}
		
		
		
		

}
