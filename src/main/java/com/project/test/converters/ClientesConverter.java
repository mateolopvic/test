package com.project.test.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.test.entidades.Clientes;
import com.project.test.modelos.ClientesModel;
import com.project.test.repositorios.ClientesRepository;

@Component
public class ClientesConverter extends Converter<ClientesModel, ClientesModel, Clientes>{

	@Autowired
	private ClientesRepository clientesRepository;

	@Override
	public ClientesModel entidadToModeloRes(Clientes entidad) {
		ClientesModel model = new ClientesModel();
		try {
			BeanUtils.copyProperties(entidad, model);
			
		}catch(Exception e) {
			log.error("Error al convertir la entidad en modelo clientes", e);
		}
		return model;
	}

	@Override
	public Clientes modeloReqToEntidad(ClientesModel model) throws Exception {
		Clientes cliente = new Clientes();
		if(model.getId() != null && !model.getId().isEmpty()) {
			cliente = clientesRepository.getById(model.getId());
		}
		try {
			BeanUtils.copyProperties(model, cliente);
			
		}catch(Exception e) {
			log.error("Error al convertir el modelo en entidad Clientes", e);
		}
		
		return cliente;
	}
	
	public List<ClientesModel> entidadesToModelos(List<Clientes> clientes){
		List<ClientesModel> models = new ArrayList<>();
		for(Clientes c : clientes) {
			models.add(entidadToModeloRes(c));
		}
		return models;
	}
}
