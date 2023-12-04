package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Comunity;
import bilgenkaanremzi.CapstoneProjectSteamClone.enums.ComunityCategory;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.NotFoundException;
import bilgenkaanremzi.CapstoneProjectSteamClone.payload.ComunityDTO;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.ComunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunityService {
    @Autowired
    private ComunityRepository comunityRepository;

    @Autowired
    private GameService gameService;
public Comunity save(ComunityDTO body) {

    Comunity newComunity = new Comunity();
    newComunity.setCategory(ComunityCategory.valueOf(body.category()));
    newComunity.setGame(gameService.findById(body.gameId()));
    newComunity.setPicture(body.picture());
    newComunity.setTitle(body.title());
    newComunity.setVideo(body.video());
    newComunity.setContent(body.content());
    return comunityRepository.save(newComunity);
}

public Comunity findById(long id) {
    return comunityRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
}

public Page<Comunity> getComunity(int page, int size, String orderBy, long gameId) {
    Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
    List<Comunity> comunities= comunityRepository.findAll();
    if (gameId != 0) {
        comunities.stream().filter(f -> f.getGame().getId() == gameId).collect(Collectors.toList());
    }
    return new PageImpl<>(comunities,pageable,comunities.size());
}

public void findAndDeleteById(long id) {
    Comunity found = this.findById(id);
    comunityRepository.delete(found);
}
}
