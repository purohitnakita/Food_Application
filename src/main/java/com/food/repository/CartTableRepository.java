package com.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Cart_Table;

@Repository
public interface CartTableRepository extends JpaRepository<Cart_Table, Integer> {

}
