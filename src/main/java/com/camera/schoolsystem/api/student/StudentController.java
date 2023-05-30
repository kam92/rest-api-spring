package com.camera.schoolsystem.api.student;

import com.camera.schoolsystem.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Students", description = "Operations for students")
@RestController
@RequestMapping("/students")
public class StudentController extends AbstractController<StudentService> {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Retrieve a list of all Students",
            description = "A list of all registered students.")
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @Operation(
            summary = "Retrieve a PDF file of all Students",
            description = "A report of all students")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportStudentsToPDF() {
        return studentService.exportStudentsToPDF();
    }

    @Operation(
            summary = "Retrieve a Student by ID",
            description = "Provide a valid ID.")
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        StudentEntity student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);

    }

    @Operation(
            summary = "Create a new Student",
            description = "The ID value will be ignored, the database handles ID.")
    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
        StudentEntity createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @Operation(
            summary = "Update a Student by ID.",
            description = "Update fields on an existing Student")
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity student) {
        StudentEntity updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(
            summary = "Delete a Student by ID.",
            description = "Delete a Student from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.ok(null);
        }
        return null;
    }
}