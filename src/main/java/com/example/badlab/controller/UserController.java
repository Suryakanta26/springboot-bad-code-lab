
package com.example.badlab.controller;
import org.springframework.web.bind.annotation.*;
@RestController
public class UserController{
@GetMapping("/user/{name}")
public String get(@PathVariable String name){
try{
if(name.equals("admin")) return "ADMIN";
if(name.length()>10){if(name.startsWith("a")){if(name.endsWith("z")){return name;}}}
}catch(Exception e){}
return null;
}}