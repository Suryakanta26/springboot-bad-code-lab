
package com.example.badlab.controller;
import org.springframework.web.bind.annotation.*;
@RestController
public class OrderController{
@GetMapping("/orders")
public String orders(){System.out.println("called"); return "ok";}
}