package bilgenkaanremzi.CapstoneProjectSteamClone.controlers;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.BadRequestException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.GamePostDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.GamePutDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Game saveGame(@RequestBody @Validated GamePostDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return gameService.save(body);
        }
    }

    @GetMapping("")
    public Page<Game> getGameByFilter(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id")String orderBy,
                                      @RequestParam(defaultValue = "")String category,
                                      @RequestParam(defaultValue = "") String title,
                                      @RequestParam(defaultValue = "0")int year,@RequestParam(defaultValue = "0") double priceLess,@RequestParam(defaultValue = "0") double priceGreater
                                      ) {
        return gameService.getGames(page, size, orderBy, category, title, year, priceLess, priceGreater);
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable long id) {
        return gameService.findById(id);
    }
    @PutMapping("/{id}")
    public Game findAndUpdateById(@PathVariable long id, @RequestBody @Validated GamePutDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else  {
            return gameService.findAndUpdateById(id, body);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDeleteById(@PathVariable long id) {
        gameService.findAndDeleteById(id);
    }


    @PostMapping("/upload/{id}")
    public Game uploadPreview(@PathVariable long id, @RequestParam("preview")MultipartFile body)throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return gameService.uploadPicture(id, body);
    }
}
