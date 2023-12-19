package bilgenkaanremzi.CapstoneProjectSteamClone.services;

import bilgenkaanremzi.CapstoneProjectSteamClone.entities.Game;
import bilgenkaanremzi.CapstoneProjectSteamClone.entities.User;
import bilgenkaanremzi.CapstoneProjectSteamClone.exceptions.NotFoundException;
import bilgenkaanremzi.CapstoneProjectSteamClone.repositories.UserRepository;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameService gameService;

    public Page<User> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return userRepository.findAll(pageable);

    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with the email " + email + " not Found"));
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User with Username " +username + " not found!"));
    }
    public User findById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User changeDisplayName(long id , User body) throws NotFoundException {
        User found = this.findById(id);
        found.setDisplayName(body.getDisplayName());
        return userRepository.save(found);
    }

    public void findByIdandDelete(long id) throws NotFoundException {
        User found =this.findById(id);
        userRepository.delete(found);
    }

    public User purchaseGame(long id, String gamesBought) {
        User found = this.findById(id);
        List<String> myList = new ArrayList<String>(Arrays.asList(gamesBought.split(",")));
        myList.forEach((gameId) -> found.getOwnedGames().add(gameService.findById(Long.parseLong(gameId))));
        return userRepository.save(found);
    }
}
