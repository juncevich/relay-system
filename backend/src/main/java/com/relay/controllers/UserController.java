package com.relay.controllers;

import com.relay.exeptions.RelayNotFoundException;
import com.relay.model.Relay;
import com.relay.model.User;
import com.relay.repository.UserRepository;
import com.relay.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final RelayService relayService;

    private final UserRepository userRepository;

    @Autowired
    public UserController(RelayService relayService, UserRepository userRepository) {
        this.relayService = relayService;
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllRelays() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveRelay(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new RelayNotFoundException("id - " + id);

        Resource<User> resource = new Resource<>(user.get());

        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllRelays());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createRelay(@Valid @RequestBody Relay relay) {
        Relay savedRelay = relayService.save(relay);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRelay.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
