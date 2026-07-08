
package com.example.badlab.service;
import org.springframework.stereotype.Service;
@Service
public class UserService{
private static final String PASSWORD="admin123";
public String query(String name){return "SELECT * FROM users WHERE name = ?";}
}