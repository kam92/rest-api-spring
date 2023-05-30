package com.camera.schoolsystem.student;

import com.camera.schoolsystem.api.student.StudentEntity;
import com.camera.schoolsystem.api.student.StudentRepository;
import com.camera.schoolsystem.api.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        List<StudentEntity> students = new ArrayList<>();
        students.add(new StudentEntity());
        students.add(new StudentEntity());
        when(studentRepository.findAll()).thenReturn(students);

        List<StudentEntity> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById_ValidId() {
        Long id = 1L;
        StudentEntity student = new StudentEntity();
        student.setId(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        StudentEntity result = studentService.getStudentById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void testGetStudentById_InvalidId() {
        Long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.getStudentById(id));
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void testCreateStudent() {
        StudentEntity student = new StudentEntity();
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(student);

        StudentEntity result = studentService.createStudent(student);

        assertNotNull(result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testUpdateStudent_ValidId() {
        Long id = 1L;
        StudentEntity existingStudent = new StudentEntity();
        existingStudent.setId(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(existingStudent));

        StudentEntity updatedStudent = new StudentEntity();
        updatedStudent.setId(id);
        updatedStudent.setFirstName("John");

        when(studentRepository.save(any(StudentEntity.class))).thenReturn(updatedStudent);

        StudentEntity result = studentService.updateStudent(id, updatedStudent);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, times(1)).save(existingStudent);
    }

    @Test
    void testUpdateStudent_InvalidId() {
        Long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        StudentEntity updatedStudent = new StudentEntity();
        updatedStudent.setId(id);
        updatedStudent.setFirstName("John");

        StudentEntity result = studentService.updateStudent(id, updatedStudent);

        assertNull(result);
        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, never()).save(any(StudentEntity.class));
    }

    @Test
    void testExportStudentsToPDF() {
        List<StudentEntity> students = new ArrayList<>();
        students.add(new StudentEntity());
        students.add(new StudentEntity());
        when(studentRepository.findAll()).thenReturn(students);

        ResponseEntity<byte[]> response = studentService.exportStudentsToPDF();

        assertNotNull(response);
        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testDeleteStudent_ValidId() {
        Long id = 1L;
        StudentEntity student = new StudentEntity();
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        boolean result = studentService.deleteStudent(id);

        assertTrue(result);
        verify(studentRepository, times(1)).findById(id);
        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteStudent_InvalidId() {
        StudentEntity student = new StudentEntity();
        student.setId(1L);
        when(studentRepository.findById(student.getId())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> {
            studentService.deleteStudent(student.getId());
        });

        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentRepository, never()).deleteById(any(Long.class));
    }
}
