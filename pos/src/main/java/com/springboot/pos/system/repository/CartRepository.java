package com.springboot.pos.system.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.pos.system.entity.Cart;

@Repository

public interface CartRepository extends CrudRepository<Cart, Long> {
	List<Cart> findByName(String name);
}
