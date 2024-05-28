package com.csc340.security_demo.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Get all users
     *
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    /**
     * Find one user by ID.
     *
     * @param id
     * @return the user
     */
    public User getUser(long id) {
        return repo.getReferenceById(id);
    }

    /**
     * Delete user by ID.
     *
     * @param id
     */
    public void deleteUser(long id) {
        repo.deleteById(id);
    }

    /**
     * Save user entry.
     *
     * @param user
     */
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    /**
     * Update existing user.
     *
     * @param user
     */
    public void updateUser(User user) {
        repo.save(user);
    }

    public User getUserByUserName(String userName) {
        return repo.findByUserName(userName).orElseThrow(()
                -> new UsernameNotFoundException(userName + "not found"));
    }
}