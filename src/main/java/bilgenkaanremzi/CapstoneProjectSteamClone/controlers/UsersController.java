package bilgenkaanremzi.CapstoneProjectSteamClone.controlers;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.User;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.BadRequestException;
import bilgenkaanremzi.CapstoneProjectSteamClone.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users",description = "API Users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("")
public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,@RequestParam(defaultValue = "id") String orderBy) {
        return userService.getUsers(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
public User findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated User body , BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return userService.changeDisplayName(id, body);
        }
    }

    @GetMapping("/me")
    @Transactional
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser) {
       System.out.println(currentUser);
        return currentUser;
    }
    @PutMapping("/me")
    public User findByProfileAndUpdate(@AuthenticationPrincipal User currentUser, @RequestBody @Validated User body , BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return userService.changeDisplayName(currentUser.getId(), body);
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdanddelete(@PathVariable long id) {
        userService.findByIdandDelete(id);
    }
}
