package com.example.service;

import com.example.model.Model;

import java.util.List;
import java.util.Optional;

public interface Service {
    Model createStudent(Model model);
    List<Model> getAllStudents();
    Optional<Model> getStudentById(int id);
    Model updateStudent(Model model);
    void deleteStudent(int id);


}
