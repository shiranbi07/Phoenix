package com.example.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @PostMapping("/checkCode")
    public ResponseEntity<String> test(@RequestParam(value = "filePath", defaultValue ="https://github.com/spring-guides/gs-spring-boot/blob/main/initial/src/main/java/com/example/springboot/Application.java") Object filePath) {
        try {
            String res = Helper.getResponse( filePath.toString());
			return ResponseEntity.ok("res");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
