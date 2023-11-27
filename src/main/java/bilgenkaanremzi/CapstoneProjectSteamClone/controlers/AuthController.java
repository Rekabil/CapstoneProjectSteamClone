package bilgenkaanremzi.CapstoneProjectSteamClone.controlers;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.User;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.BadRequestException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.NewUserDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.UserLoginDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.UserLoginSuccessDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.services.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth",description = "API Users")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.save(body);
            } catch (IOException e ){
                throw new RuntimeException(e);
            }
        }
    }
}
