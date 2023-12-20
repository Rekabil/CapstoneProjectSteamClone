package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import java.util.List;

public record GamePutDTO(
        double salePrice,
        String preview,
        String description,
        String details
) {
}
