package com.project.test.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Clientes implements Serializable{
	
	private static final long serialVersionUID = 8680136741369457332L;
    @Id
    @UuidGenerator	 
    private String id;
	 
	 private String nombre;
	 private String apellido;
	 private String cuit;
	 private String email;
	 private String telefonoCelular;
	 private String razonSocial;
	 
	 @JsonFormat(pattern = "yyyy-MM-dd")
	 private LocalDate fechaNacimiento;

	 private LocalDate baja;
	 
}
