package com.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.model.User_Table;


@Repository
public interface UserTableRepository extends JpaRepository<User_Table, Integer> {

}
