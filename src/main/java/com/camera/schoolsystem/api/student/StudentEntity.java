package com.camera.schoolsystem.api.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int grade;

    private String address;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "users_id")
    private Long user;

}
