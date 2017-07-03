package app;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/")
    public Hello index() {
        return new Hello("Spring working");
    }
}