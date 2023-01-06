package com.springboot.pos.system.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.pos.system.entity.Dash;

@Repository

public interface DashRepository extends CrudRepository<Dash, Long> {
	List<Dash> findById(String id);
	//List<Dash> findByDate(Date dateTime);


}
