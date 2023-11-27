package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Country;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;


    private static Country getCountry(CSVRecord record) {
        Country country = new Country();
        country.setName(record.get(0));
        country.setCode(record.get(1));
        return country;
    }

    public void loadCountryCSV(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(',').parse(reader);
            for (CSVRecord record : records) {
                Country country =  getCountry(record);
                countryRepository.save(country);
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
    public List<Country> FindAll() {return countryRepository.findAll();}

    public Country FindByName(String s ) {
        return countryRepository.findByName(s).orElseThrow(() -> new RuntimeException("Country not found")
        ) ;
    }
}
