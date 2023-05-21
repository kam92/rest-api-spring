package com.camera.schoolsystem.user;

import com.camera.schoolsystem.enumeration.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private UserRoleEnum role;

    @Column(name = "blocked", columnDefinition = "SMALLINT", nullable = false)
    private short blocked;

}
