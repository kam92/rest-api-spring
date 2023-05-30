package com.camera.schoolsystem.api.user;

import com.camera.schoolsystem.enumeration.UserRoleEnum;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private UserRoleEnum role;
    private Short blocked;

}
