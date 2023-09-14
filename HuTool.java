package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class HuTool {

    
    public static void main(String[] args) {
	SpringApplication.run(HuTool.class, args);
    }

    @GetMapping("/out_of_memory_1")
    public void out_of_memory_1() {
	System.out.println("FFFFFFFFFF");
    }
}
