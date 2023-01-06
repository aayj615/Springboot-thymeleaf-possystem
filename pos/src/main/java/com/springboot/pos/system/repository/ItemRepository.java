package com.springboot.pos.system.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.pos.system.entity.Item;

@Repository
//@NoRepositoryBean

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByName(String name);
    
}
