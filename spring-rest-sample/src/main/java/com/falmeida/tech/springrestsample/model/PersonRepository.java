package com.falmeida.tech.springrestsample.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person,Integer>{

	public Person findByName(@Param("name") String name);
	
}
