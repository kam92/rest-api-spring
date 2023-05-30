package com.camera.schoolsystem.api.user;

import com.camera.schoolsystem.enumeration.UserRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDto {

    @Schema(type = "number", format = "long", description = "ID", example = "2")
    private Long id;
    private String username;
    private UserRoleEnum role;
    private Short blocked;

}
