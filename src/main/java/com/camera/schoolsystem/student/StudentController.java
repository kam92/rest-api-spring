package com.camera.schoolsystem.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Students", description = "Operations for students")
@RestController
@RequestMapping("/students")

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Retrieve a list of all Students",
            description = "A list of all registered students.",
            tags = {"students", "get"})
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @Operation(
            summary = "Retrieve a Student by ID",
            description = "Provide a valid ID.",
            tags = {"students", "get"})
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        StudentEntity student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Create a new Student",
            description = "The ID value will be ignored, the database handles ID.",
            tags = {"students", "post"})
    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
        StudentEntity createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @Operation(
            summary = "Update an Student by ID.",
            description = "Update fields on an existing Student",
            tags = {"students", "post"})
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity student) {
        StudentEntity updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Delete a Student by ID.",
            description = "Delete a Student from the database.",
            tags = {"students", "delete"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}