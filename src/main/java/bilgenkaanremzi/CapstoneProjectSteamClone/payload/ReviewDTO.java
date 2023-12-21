package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReviewDTO(
        @NotEmpty(message = "Title cannot be empty")
        String title,
        String content,
        @NotNull(message = "Rating cannot be empty")
        int rating,
        @NotNull(message = "Game Id cannot be empty")
        long gameId
) {
}
