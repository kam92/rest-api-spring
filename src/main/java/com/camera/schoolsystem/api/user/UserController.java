package com.camera.schoolsystem.api.user;

import com.camera.schoolsystem.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Operations for users")
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<UserService> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Retrieve a list of all Users",
            description = "A list of all registered users.")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> returnList = userService.getAllUsers();
        return ResponseEntity.ok(returnList);
    }

    @Operation(
            summary = "Retrieve an User by ID",
            description = "Provide a valid ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @Operation(
            summary = "Create a new User",
            description = "The ID value will be ignored, the database handles ID.")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserEntity user) {
        UserDto userDto = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @Operation(
            summary = "Update an User by ID.",
            description = "Update fields on an existing User")
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity student) {
        UserEntity updatedStudent = userService.updateUser(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(
            summary = "Delete an User by ID.",
            description = "Delete an User from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(null);

    }
}