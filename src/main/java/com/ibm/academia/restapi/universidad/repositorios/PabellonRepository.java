package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Long>{

	public Iterable<Pabellon> findByDireccionLocalidad(String localidad);
	public Iterable<Pabellon> findByNombre(String nombre);
}
