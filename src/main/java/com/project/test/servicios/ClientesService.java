package com.project.test.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.converters.ClientesConverter;
import com.project.test.entidades.Clientes;
import com.project.test.modelos.ClientesModel;
import com.project.test.repositorios.ClientesRepository;

@Service
public class ClientesService {

	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private ClientesConverter clientesConverter;
	
	public ClientesModel guardar(ClientesModel model) throws Exception {
		Clientes cliente = clientesConverter.modeloReqToEntidad(model);
		
		validar(cliente);
		
		return clientesConverter.entidadToModeloRes(clientesRepository.save(cliente));
	}
	
	public String eliminar(String id) throws Exception {
		Clientes cliente = clientesRepository.getById(id);
		
		if(cliente == null) {
			throw new Exception("El cliente que intenta eliminar no existe o ya fue dado de baja");
		}
		cliente.setBaja(LocalDate.now());
		
		return clientesRepository.save(cliente).getId();
	}
	
	public List<ClientesModel> getAll(){
		return clientesConverter.entidadesToModelos(clientesRepository.findAll());
	}
	
	public List<ClientesModel> getBySearchParam(String searchParam){
		return clientesConverter.entidadesToModelos(clientesRepository.getBySearchParam("%" + searchParam + "%"));
	}
	
	public void validar(Clientes cliente) throws Exception {
		if(cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
			throw new Exception("El cliente debe tener un nombre");
		}
		if(cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
			throw new Exception("El cliente debe tener un apellido");
		}
		if(cliente.getCuit() == null || cliente.getCuit().isEmpty()) {
			throw new Exception("El cliente debe tener un cuit");
		}else if (!cliente.getCuit().matches("\\d{2}-\\d{8}-\\d")) {
			throw new Exception("El cliente debe tener un cuit válido");
        }
		if(cliente.getTelefonoCelular() == null || cliente.getTelefonoCelular().isEmpty()) {
			throw new Exception("El cliente debe tener un teléfono celular");
		}
		if(cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
			throw new Exception("El cliente debe tener un email");
		}else if(!cliente.getEmail().matches("^[\\w-+.]+(\\.[\\w-]{1,62}){0,126}@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}")) {
			throw new Exception("El cliente debe tener un email válido");
		}
		if(cliente.getRazonSocial() == null || cliente.getRazonSocial().isEmpty()) {
			throw new Exception("El cliente debe tener una razón social");
		}
	}
}
