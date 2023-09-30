package com.example.controller;

import com.example.*;
import com.example.mapper.Mapper;
import com.example.model.Model;
import com.example.service.ServiceImplementation;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller extends GrpcCrud2ServiceGrpc.GrpcCrud2ServiceImplBase {
    @Inject
    private ServiceImplementation serviceImplementation;

    //Similar to create
    @Override
    public void send(GrpcCrud2Request request, StreamObserver<GrpcCrud2Reply> responseObserver) {
        Model model = Model.builder().name(request.getName()).phone(request.getPhone()).build();
        Model student = serviceImplementation.createStudent(model);

        GrpcCrud2Reply grpcCrud2Reply = GrpcCrud2Reply.newBuilder().setMessage("Hello" + student.getName()).build();
        responseObserver.onNext(grpcCrud2Reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getStudent(Id request, StreamObserver<Student> responseObserver) {
        Optional<Model> model = serviceImplementation.getStudentById(request.getId());
        if (model.isEmpty())
            System.out.println("Id " + request.getId() + " doesn't exists");
        else {
            Student student = Student.newBuilder()
                    .setName(model.get().getName())
                    .setId(model.get().getId())
                    .setPhone(model.get().getPhone()).build();
            responseObserver.onNext(student);
            responseObserver.onCompleted();
        }

    }

    @Override
    public void deleteStudent(Id request, StreamObserver<GrpcCrud2Reply> responseObserver) {
        Optional<Model> model = serviceImplementation.getStudentById(request.getId());
        if (!model.isEmpty()) {

            serviceImplementation.deleteStudent(request.getId());
            GrpcCrud2Reply grpcCrud2Reply = GrpcCrud2Reply.newBuilder().setMessage("Student with id= " + request.getId() + " is deleted.").build();
            responseObserver.onNext(grpcCrud2Reply);
            responseObserver.onCompleted();
        } else {
            GrpcCrud2Reply grpcCrud2Reply = GrpcCrud2Reply.newBuilder().setMessage("Student with id= " + request.getId() + " doesn't exists.").build();
            responseObserver.onNext(grpcCrud2Reply);
            responseObserver.onCompleted();
        }

    }

    @Override
    public void updateStudent(Student request, StreamObserver<Student> responseObserver) {
        Optional<Model> model = serviceImplementation.getStudentById(request.getId());

        if (model.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND.asException());
            System.out.println("Not found of if....");
        } else {
            Model model1 = Mapper.mapToModel(request);

            serviceImplementation.updateStudent(model1);
            Student student = Student.newBuilder()
                    .setName(request.getName())
                    .setPhone(request.getPhone()).build();

            responseObserver.onNext(student);
            responseObserver.onCompleted();
            System.out.println("I am at else");

        }

    }

    @Override
    public void showAllStudents(Empty request, StreamObserver<Student2> responseObserver) {
        List<Model> studentList = serviceImplementation.getAllStudents();
        Student2 student2 =Mapper.mapModelToStudent2(studentList);
        responseObserver.onNext(student2);
        responseObserver.onCompleted();

    }
}
