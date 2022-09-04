package web.controllers;

import org.springframework.web.bind.annotation.*;
import web.dto.ProfileDto;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @GetMapping
    public ProfileDto getCurrentUserInfo(@RequestHeader String username) {
        return new ProfileDto(username);
    }
}
