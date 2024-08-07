package com.on5Aug.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.on5Aug.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
