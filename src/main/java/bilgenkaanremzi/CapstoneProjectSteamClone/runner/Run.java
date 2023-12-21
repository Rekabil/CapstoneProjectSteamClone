package bilgenkaanremzi.CapstoneProjectSteamClone.runner;

import bilgenkaanremzi.CapstoneProjectSteamClone.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Run implements CommandLineRunner {
    @Autowired
    CountryService countryService;
    @Override
    public void run(String... args) throws Exception {


// countryService.loadCountryCSV("C:/Users/Rekabil/Desktop/CapstoneProjectSteamClone/src/main/java/bilgenkaanremzi/CapstoneProjectSteamClone/file/data_csv.csv");

    }
}
