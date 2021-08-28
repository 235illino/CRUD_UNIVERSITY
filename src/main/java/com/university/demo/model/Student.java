package com.university.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
