package com.camera.schoolsystem.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Operations for users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Retrieve a list of all Users",
            description = "A list of all registered users.")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(
            summary = "Retrieve an User by ID",
            description = "Provide a valid ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        if (userService.getUserById(id) != null) {
            return ResponseEntity.ok(userService.getUserById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Create a new User",
            description = "The ID value will be ignored, the database handles ID.")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @Operation(
            summary = "Update an User by ID.",
            description = "Update fields on an existing User")
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity student) {
        UserEntity updatedStudent = userService.updateUser(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Delete an User by ID.",
            description = "Delete an User from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}