package org.example.demo.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data // this auto generates getters, setters, equals, hashcode, and toString for all fields
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;
    private String email;
    private String school;
    private String major;
    private String org;
}