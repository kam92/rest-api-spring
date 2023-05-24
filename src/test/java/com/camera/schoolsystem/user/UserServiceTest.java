package com.camera.schoolsystem.user;

import com.camera.schoolsystem.enumeration.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ReturnsListOfUserDtos() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity(1L, "user1", "password1", UserRoleEnum.USUARIO, (short) 0));
        users.add(new UserEntity(2L, "user2", "password2", UserRoleEnum.USUARIO, (short) 0));
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> userDtos = userService.getAllUsers();
        assertEquals(2, userDtos.size());
        assertEquals("user1", userDtos.get(0).getUsername());
        assertEquals("user2", userDtos.get(1).getUsername());
    }

    @Test
    void getUserById_WithValidId_ReturnsUserDto() {
        UserEntity user = new UserEntity(1L, "user1", "password1", UserRoleEnum.USUARIO, (short) 0);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUserById(1L);

        assertEquals("user1", userDto.getUsername());
        assertEquals(UserRoleEnum.USUARIO, userDto.getRole());
    }

    @Test
    void getUserById_WithInvalidId_ThrowsNoSuchElementException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getUserById(1L));
    }

    @Test
    void createUser_ReturnsCreatedUserDto() {
        UserEntity user = new UserEntity(null, "user1", "password1", null, (short) 0);
        UserEntity savedUser = new UserEntity(1L, "user1", "encodedPassword1", UserRoleEnum.USUARIO, (short) 0);
        when(userRepository.save(user)).thenReturn(savedUser);

        UserDto userDto = userService.createUser(user);

        assertEquals("user1", userDto.getUsername());
        assertEquals(UserRoleEnum.USUARIO, userDto.getRole());
        assertNotNull(userDto);
    }


    @Test
    void updateUser_WithValidId_ReturnsUpdatedUserEntity() {
        UserEntity existingUser = new UserEntity(1L, "user1", "password1", UserRoleEnum.USUARIO, (short) 0);
        UserEntity updatedUser = new UserEntity(null, "updatedUser1", "newPassword1", UserRoleEnum.ADMIN, (short) 0);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        UserEntity result = userService.updateUser(1L, updatedUser);

        assertEquals("newPassword1", result.getPassword());
        assertEquals(UserRoleEnum.ADMIN, result.getRole());
    }

    @Test
    void updateUser_WithInvalidId_ThrowsNoSuchElementException() {
        UserEntity updatedUser = new UserEntity(null, "updatedUser1", "newPassword1", UserRoleEnum.ADMIN, (short) 0);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.updateUser(1L, updatedUser));
    }

    @Test
    void deleteUser_WithValidId_ReturnsTrue() {
        UserEntity user = new UserEntity(1L, "user1", "password1", UserRoleEnum.USUARIO, (short) 0);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_WithInvalidId_ThrowsNoSuchElementException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.deleteUser(1L));
    }
}
