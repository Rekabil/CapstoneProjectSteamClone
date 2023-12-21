package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
        String categories,
        @NotNull
        LocalDate releaseDate,


        double price,
        @NotEmpty(message = "Developer Empty")
        String developer,
        @NotEmpty(message = "Publisher Empty")
        String publisher
) {

}
