package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import jakarta.validation.constraints.NotEmpty;

public record ReviewDTO(
        @NotEmpty(message = "Title cannot be empty")
        String title,
        String content,
        @NotEmpty(message = "Rating cannot be empty")
        int rating,
        @NotEmpty(message = "Game Id cannot be empty")
        long gameId
) {
}
