package com.camera.schoolsystem.user;

import com.camera.schoolsystem.enumeration.UserRoleEnum;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private UserRoleEnum role;
    private Short blocked;

}
