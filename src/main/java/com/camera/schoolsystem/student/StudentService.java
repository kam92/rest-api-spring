package com.camera.schoolsystem.student;

import jakarta.transaction.Transactional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
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

    public ResponseEntity<byte[]> exportStudentsToPDF() {
        List<StudentEntity> students = getAllStudents();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Add content to the PDF document based on the student information
            int yOffset = 700;
            for (StudentEntity student : students) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                String line = "Name: " + student.getFirstName() + " " + student.getLastName() + " | Gender: " + student.getGender() + " | Contact: " + student.getContactNumber();
                float lineWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(line) / 1000 * 12;
                float xOffset = (page.getMediaBox().getWidth() - lineWidth) / 2;

                contentStream.newLineAtOffset(xOffset, yOffset);
                contentStream.showText(line);
                contentStream.endText();
                yOffset -= 20;
            }

            contentStream.close();

            document.save(baos);
            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "students.pdf");

            return ResponseEntity.ok().headers(headers).body(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public boolean deleteStudent(Long id) {
        studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return true;


    }
}