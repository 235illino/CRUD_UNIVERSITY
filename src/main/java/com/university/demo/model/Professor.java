package com.university.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First Name should not be empty")
    @Size(min = 2, max = 45, message = "First Name should be between 2 and 45 characters")
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 2, max = 45, message = "Last Name should be between 2 and 45 characters")
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

