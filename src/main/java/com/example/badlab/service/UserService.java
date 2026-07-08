
package com.example.badlab.service;
import org.springframework.stereotype.Service;
@Service
public class UserService{

public String query(String name){
    if(name == null || name.isEmpty() || !name.matches("[a-zA-Z0-9_]+")) {
        throw new IllegalArgumentException("Invalid name parameter");
    }
    return jdbcTemplate.queryForObject("SELECT * FROM users WHERE name = ?", new Object[]{name}, User.class);
}
}