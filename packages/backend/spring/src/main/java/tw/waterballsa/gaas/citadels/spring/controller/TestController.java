package tw.waterballsa.gaas.citadels.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping(value = "/hello-world")
    public String getHelloWorld() {
        return "hello world!";
    }

}
