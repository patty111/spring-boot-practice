package org.example.demo.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/main")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(required = false, defaultValue = "LINE") String name) {
        return "Hello, " + name + "!";
    }

}