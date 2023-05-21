package com.camera.schoolsystem.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public StudentEntity updateStudent(Long id, StudentEntity updatedStudent) {
        StudentEntity existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setGrade(updatedStudent.getGrade());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setParentName(updatedStudent.getParentName());
            existingStudent.setContactNumber(updatedStudent.getContactNumber());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public boolean deleteStudent(Long id) {
        try {
            studentRepository.findById(id).get();
            studentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

    }
}