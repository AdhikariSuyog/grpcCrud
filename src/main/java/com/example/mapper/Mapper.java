package com.example.mapper;

import com.example.Student;
import com.example.Student2;
import com.example.model.Model;

import java.util.List;

public class Mapper {
        public static Model mapToModel(Student request) {
            Model model = new Model();
            model.setId(request.getId());
            model.setName(request.getName());
            model.setPhone(request.getPhone());
            return model;
        }

    public static Student2 mapModelToStudent2(List<Model> modelList) {
        Student2.Builder student2Builder = Student2.newBuilder();
        for (Model model : modelList) {
            Student student = Student.newBuilder()
                    .setId(model.getId())
                    .setName(model.getName())
                    .setPhone(model.getPhone())
                    .build();
            student2Builder.addStudents(student);
        }
        return student2Builder.build();
    }
}
