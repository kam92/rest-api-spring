package com.camera.schoolsystem.api.user;

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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRoleEnum role;

    @Column(nullable = false)
    private short blocked;

}
