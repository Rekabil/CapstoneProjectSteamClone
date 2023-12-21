package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.User;
import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Role;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.UnauthorizedException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.NewUserDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.UserLoginDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.CountryRepository;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.UserRepository;
import bilgenkaanremzi.CapstoneProjectSteamClone.security.JWTTools;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private CountryRepository countryRepository;

    public String authenticateUser(UserLoginDTO body) {
        User user = userService.findByUsername(body.username());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credentials not valid");
        }
    }

    public User save(NewUserDTO body) throws IOException {
        userRepository.findByUsername(body.username()).ifPresent(user -> {
            try {
                throw new BadRequestException("Username is Already in use");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        User newUser = new User();
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setDisplayName(body.displayName());
       newUser.setCountry(countryRepository.findByName(body.country()).orElse(null));
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);

        return userRepository.save(newUser);
    }
}
