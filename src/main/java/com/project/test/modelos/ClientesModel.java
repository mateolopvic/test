package com.project.test.modelos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClientesModel {

	private String id;
	 
	 private String nombre;
	 private String apellido;
	 private String cuit;
	 private String email;
	 private String telefonoCelular;
	 private String razonSocial;
	 
	 private LocalDate fechaNacimiento;

}
