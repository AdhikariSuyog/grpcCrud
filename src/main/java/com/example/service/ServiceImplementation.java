package com.example.service;

import com.example.model.Model;
import com.example.repository.StudentRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class ServiceImplementation implements Service {
   @Inject
    private final StudentRepository studentRepository;

    public ServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Model createStudent(Model model) {
        return studentRepository.save(model);
    }

    @Override
    public List<Model> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Model> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Model updateStudent(Model model) {
        return studentRepository.update(model);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }


}
