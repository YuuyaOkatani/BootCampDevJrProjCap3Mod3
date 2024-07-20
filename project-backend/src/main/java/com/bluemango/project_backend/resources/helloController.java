package com.bluemango.project_backend.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }



    
}
