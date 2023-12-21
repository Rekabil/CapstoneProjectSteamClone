package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import bilgenkaanremzi.CapstoneProjectSteamClone.enums.Category;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.NotFoundException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.GamePostDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.GamePutDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.GameRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Game save(GamePostDTO body) {
        Game newGame = new Game();
        newGame.setTitle(body.title());
        newGame.setPrice(body.price());
List<Category> categories = new ArrayList<>();
        List<String> myList = new ArrayList<String>(Arrays.asList(body.categories().split(",")));
        myList.forEach(category -> categories.add(Category.valueOf(category)));
        newGame.setCategories(categories);
        newGame.setDetails(body.details());
        newGame.setReleaseDate(body.releaseDate());
        newGame.setYear(body.releaseDate().getYear());
        newGame.setDescription(body.description());
        newGame.setDeveloper(body.developer());
        newGame.setPublisher(body.publisher());
        return gameRepository.save(newGame);
    }

    public Game findById(long id) {
        return gameRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Game findAndUpdateById(long id, GamePutDTO body) {
        Game found = this.findById(id);
found.setSalePrice(body.salePrice());
found.setPreview(body.preview());
found.setDescription(body.description());
found.setDetails(body.details());
return gameRepository.save(found);
    }

public Page<Game> getGames(int page , int size, String orderBy, String category, String title,int year, double priceLess, double priceGreater) {
    Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
    List<Game> games = gameRepository.findAll();
    if (!category.isEmpty()) {
       games = games.stream().filter(f-> f.getCategories().contains(Category.valueOf(category))).collect(Collectors.toList());
    }
    if (!title.isEmpty()) {
        games = games.stream().filter(f-> f.getTitle().contains(title)).collect(Collectors.toList());
    }
    if (year != 0) {
        games = games.stream().filter(f-> f.getYear() == year).collect(Collectors.toList());
    }
    if (priceGreater != 0 ) {
        games = games.stream().filter(f -> f.getPrice() > priceGreater).collect(Collectors.toList());
    }
    if (priceLess != 0) {
        games = games.stream().filter(f-> f.getPrice() < priceLess).collect(Collectors.toList());
    }
    return new PageImpl<>(games,pageable,games.size());
}

public void findAndDeleteById(long id) {
        Game found = this.findById(id) ;
        gameRepository.delete(found);
}

public String upload(MultipartFile file) throws IOException {
        System.out.println("upload start");
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
}


public Game uploadPicture(long id , MultipartFile file) throws NotFoundException, IOException {
        Game found = this.findById(id);
        found.setPreview(this.upload(file));
System.out.println("End Upload");
return gameRepository.save(found);
}
}
