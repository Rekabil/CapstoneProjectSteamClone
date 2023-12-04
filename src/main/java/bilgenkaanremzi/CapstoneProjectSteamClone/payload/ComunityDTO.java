package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import jakarta.validation.constraints.NotEmpty;

public record ComunityDTO(@NotEmpty(message = "Title cannot be empty") String title, @NotEmpty(message = "provide game id") long gameId, String picture, String video, @NotEmpty(message = "A Category must be choosen") String category, String content) {
}
