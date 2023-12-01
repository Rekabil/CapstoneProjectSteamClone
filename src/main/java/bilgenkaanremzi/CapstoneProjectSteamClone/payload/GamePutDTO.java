package bilgenkaanremzi.CapstoneProjectSteamClone.payload;

import java.util.List;

public record GamePutDTO(
        double salePrice,
        List<String> preview,
        String description,
        String details
) {
}
