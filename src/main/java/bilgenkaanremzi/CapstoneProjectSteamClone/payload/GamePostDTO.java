package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Category;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record GamePostDTO(
        @NotEmpty(message = "Please insert Title")
        String title,
        @NotEmpty(message = "Please leave a description of the game")
        String description,
        @NotEmpty(message = "Please write some details")
        String details,
        @NotEmpty(message = "please leave some categories")
        List<Category> categories,
        @NotEmpty(message = "When is the game gonna release?")
        LocalDate releaseDate,
        @NotEmpty(message = "The price of the game?")
        double price
) {

}
