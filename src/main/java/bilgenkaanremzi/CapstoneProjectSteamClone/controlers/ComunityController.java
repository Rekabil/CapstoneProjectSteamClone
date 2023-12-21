package bilgenkaanremzi.CapstoneProjectSteamClone.controlers;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comunity;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.BadRequestException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.ComunityDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.services.ComunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunities")
public class ComunityController {
    @Autowired
    private ComunityService comunityService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comunity saveComunity(@RequestBody @Validated ComunityDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return comunityService.save(body);
        }
    }

    @GetMapping("")
    public Page<Comunity> getComunity(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "0") long gameid) {
      return comunityService.getComunity(page, size, orderBy, gameid);
    }
    @GetMapping("/{id}")
public Comunity findById(@PathVariable long id) {
        return comunityService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDeleteById(@PathVariable long id) {
        comunityService.findAndDeleteById(id);
    }
}
