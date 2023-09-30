package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class Model {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String phone;

    @Override
    public String toString() {
        return  "[id = "+ id+" name =" + name+ ", phone=" + phone +"]";
    }
}
