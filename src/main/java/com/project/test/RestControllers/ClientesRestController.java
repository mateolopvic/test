package com.project.test.RestControllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.test.modelos.ClientesModel;
import com.project.test.servicios.ClientesService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesRestController {

	private static final Logger log = LoggerFactory.getLogger(ClientesRestController.class);
	
	@Autowired
	private ClientesService clientesService;
	
	@PostMapping(value = "/guardar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> guardar(@RequestBody ClientesModel model) {
		Map<String, String> response = new HashMap<>();
		try {
			response.put("id", clientesService.guardar(model).getId());
			return new ResponseEntity<>(response, HttpStatus.OK); 
		} catch (Exception e) {
			log.error(e.getMessage());
			response.put("ERROR", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestParam String id) {
		Map<String, String> response = new HashMap<>();
		try {
			response.put("id", clientesService.eliminar(id));
			return new ResponseEntity<>(response, HttpStatus.OK); 
		} catch (Exception e) {
			log.error(e.getMessage());
			response.put("ERROR", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(clientesService.getAll(), HttpStatus.OK); 
	}
	
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search() {
		return new ResponseEntity<>(clientesService.getAll(), HttpStatus.OK); 
	}
}
