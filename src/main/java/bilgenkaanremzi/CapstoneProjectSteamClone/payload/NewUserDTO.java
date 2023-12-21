package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "email cannot be empty")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is not valid")
        String email,
        @NotEmpty(message = "password cannot be empty")
        @Size(min = 8, message = "Password Too Short")
        String password,
        @NotEmpty(message = "Username Cannot be empty")
        @NotNull
        String username,
        @NotEmpty(message = "Display name cannot be empty")
        String displayName,
        @NotEmpty(message = "Country cannot be empty")
        String country

) {
}
