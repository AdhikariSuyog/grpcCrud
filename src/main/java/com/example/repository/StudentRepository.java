package com.example.repository;

import com.example.model.Model;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.Repository;
@Repository
public interface StudentRepository extends CrudRepository<Model, Integer> {
}
