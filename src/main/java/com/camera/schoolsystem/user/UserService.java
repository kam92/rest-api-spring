package com.camera.schoolsystem.user;

import com.camera.schoolsystem.enumeration.UserRoleEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDto> returnList = new ArrayList<>();

        for (UserEntity user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRole(user.getRole());
            userDto.setBlocked(user.getBlocked());
            userDto.setUsername(user.getUsername());
            returnList.add(userDto);
        }
        return returnList;
    }

    public UserDto getUserById(Long id) {
        try {
            UserEntity user = userRepository.findById(id).get();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRole(user.getRole());
            userDto.setBlocked(user.getBlocked());
            userDto.setUsername(user.getUsername());
            return userDto;
        } catch (Exception e) {
            return null;
        }
    }

    public UserDto createUser(UserEntity user) {
        user.setPassword(encodePassword(user.getPassword()));
        user.setRole(UserRoleEnum.USUARIO);
        userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setBlocked(user.getBlocked());
        userDto.setRole(user.getRole());
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        return userDto;
    }

    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setRole(updatedUser.getRole());
            existingUser.setPassword(encodePassword(updatedUser.getPassword()));
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setBlocked((short) 0);
            return userRepository.save(existingUser);
        }
        return null;
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}