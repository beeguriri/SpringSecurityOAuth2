package wendy.study.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wendy.study.springsecurity.model.User;

@RestController
@RequestMapping("/api")
public class CorsController {

    @GetMapping("/users")
    public User corsTest() {
        return new User("hello", 20);
    }
}
