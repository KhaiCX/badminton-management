package com.badmintonmanagement.service;

import com.badmintonmanagement.exception.UserNotFoundException;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
