package tw.waterballsa.gaas.citadels.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> getHelloWorld() {
        Map<String, String> response = new HashMap<>();
        response.put("msg", "hello");
        return ResponseEntity.ok(response);
    }
}
