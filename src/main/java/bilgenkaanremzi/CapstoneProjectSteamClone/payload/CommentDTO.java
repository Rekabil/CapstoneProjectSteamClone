package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import jakarta.validation.constraints.NotEmpty;

public record CommentDTO(@NotEmpty(message = "There has to be content") String content,@NotEmpty(message = "Comunity id missing") long comunityId) {
}
