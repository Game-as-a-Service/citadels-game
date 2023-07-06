package tw.waterballsa.gaas.citadels.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHelloWorld() {
        return "hello world!";
    }

}
