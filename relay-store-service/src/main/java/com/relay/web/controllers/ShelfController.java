package com.relay.web.controllers;

import com.relay.core.model.storage.Shelf;
import com.relay.core.service.ShelfService;
import com.relay.web.dto.storage.CreateShelfRequest;
import com.relay.web.dto.storage.GetAllShelvesResponse;
import com.relay.web.mappers.StorageResponseMapper;
import com.relay.web.model.storage.ShelfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shelf controller")
public class ShelfController {

    private final ShelfService shelfService;
    private final StorageResponseMapper storageResponseMapper;

    @GetMapping("/shelves")
    @Operation(summary = "Get all shelves")
    public GetAllShelvesResponse findAllShelves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = shelfService.findAll(PageRequest.of(page, size));
        var shelfResponses = storageResponseMapper.mapShelvesToResponse(pageResult.getContent());
        return new GetAllShelvesResponse(shelfResponses, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @GetMapping("/shelves/{id}")
    @Operation(summary = "Get shelf by ID")
    public ShelfResponse findShelfById(@PathVariable Long id) {
        var shelf = shelfService.findById(id);
        return storageResponseMapper.mapShelfToResponse(shelf);
    }

    @PostMapping("/shelves")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new shelf")
    public ShelfResponse createShelf(@Valid @RequestBody CreateShelfRequest request) {
        var model = Shelf.builder()
                .number(request.number())
                .capacity(request.capacity())
                .build();
        var savedShelf = shelfService.save(model, request.storageId());
        return storageResponseMapper.mapShelfToResponse(savedShelf);
    }

    @PutMapping("/shelves/{id}")
    @Operation(summary = "Update shelf")
    public ShelfResponse updateShelf(@PathVariable Long id, @Valid @RequestBody CreateShelfRequest request) {
        var model = Shelf.builder()
                .number(request.number())
                .capacity(request.capacity())
                .storageId(request.storageId())
                .build();
        var updatedShelf = shelfService.update(id, model);
        return storageResponseMapper.mapShelfToResponse(updatedShelf);
    }

    @DeleteMapping("/shelves/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete shelf")
    public void deleteShelf(@PathVariable Long id) {
        shelfService.deleteById(id);
    }
}
