package com.project.test.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.test.entidades.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, String>{

	@Query("SELECT c FROM Clientes c WHERE c.baja IS NULL AND c.id = :id")
	public Clientes getById(@Param("id") String id);
	
	@Query("SELECT c FROM Clientes c WHERE c.baja IS NULL AND c.baja IS NULL")
	public List<Clientes> getActivos();
	
	@Query("SELECT c FROM Clientes c WHERE c.baja IS NULL AND c.baja IS NULL AND c.nombre LIKE :searchParam")
	public List<Clientes> getBySearchParam(@Param("searchParam") String searchParam);
}
