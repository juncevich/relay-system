package com.relay.web.controllers;

import com.relay.core.model.storage.Shelf;
import com.relay.core.service.ShelfService;
import com.relay.web.dto.storage.CreateShelfRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shelf controller")
public class ShelfController {

    private final ShelfService shelfService;

    @GetMapping("/shelves")
    @Operation(summary = "Get all shelves")
    public List<Shelf> findAllShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return shelfService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/shelves/{id}")
    @Operation(summary = "Get shelf by ID")
    public Shelf findShelfById(@PathVariable Long id) {
        return shelfService.findById(id);
    }

    @PostMapping("/shelves")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new shelf")
    public Shelf createShelf(@Valid @RequestBody CreateShelfRequest request) {
        var model = Shelf.builder()
                .number(request.number())
                .capacity(request.capacity())
                .build();
        return shelfService.save(model, request.storageId());
    }

    @PutMapping("/shelves/{id}")
    @Operation(summary = "Update shelf")
    public Shelf updateShelf(@PathVariable Long id, @Valid @RequestBody CreateShelfRequest request) {
        var model = Shelf.builder()
                .number(request.number())
                .capacity(request.capacity())
                .storageId(request.storageId())
                .build();
        return shelfService.update(id, model);
    }

    @DeleteMapping("/shelves/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete shelf")
    public void deleteShelf(@PathVariable Long id) {
        shelfService.deleteById(id);
    }
}
