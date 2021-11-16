package com.food.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Menu_Table;

@Repository
public interface MenuTableRepository extends JpaRepository<Menu_Table, String> {

}
