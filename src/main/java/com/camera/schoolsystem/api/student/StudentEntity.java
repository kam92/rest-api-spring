package com.camera.schoolsystem.api.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.grammars.ordering.OrderingParser;

import java.time.LocalDate;
import java.util.List;

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
    @Schema(type = "number", format = "long", description = "ID", example = "2")
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30)
    @Column(nullable = false)
    @Schema(description = "Nome do estudante", example = "João")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    @Schema(description = "Sobrenome do estudante", example = "Silva")
    private String lastName;

    @NotEmpty
    @Column(nullable = false)
    @Schema(description = "Data de Nascimento", example ="2004-12-25")
    private LocalDate dateOfBirth;

    @NotEmpty
    @Column(nullable = false)
    @Schema(description = "Gênero", example = "Masculino")
    private String gender;

    @NotEmpty
    @Size(max = 2)
    @Column(nullable = false)
    @Schema(description = "Série", example = "7")
    private int grade;

    @NotEmpty
    @Column(nullable = false)
    @Schema(description = "Série", example = "7")
    private String address;

    @NotEmpty
    @Column(nullable = false)
    @Schema(description = "Nomes dos responsáveis autorizados", example = "João, Maria")
    private List<String> parentName;

    @NotEmpty
    @Size(min = 12,max = 12)
    @Column(nullable = false)
    @Schema(description = "Contato do Responsável", example = "55992239401")
    private String contactNumber;

    @NotEmpty
    @Column(nullable = false)
    @Schema(description = "ID do usuário do aluno", example = "1")
    private Long user;

}
