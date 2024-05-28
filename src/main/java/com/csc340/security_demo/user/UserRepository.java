package com.csc340.security_demo.user;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}