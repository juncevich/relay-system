package com.relay.web.controllers;

import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.core.service.StorageService;
import com.relay.web.dto.storage.CreateRelayCabinetRequest;
import com.relay.web.dto.storage.CreateStandRequest;
import com.relay.web.dto.storage.CreateWarehouseRequest;
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
@Tag(name = "Storage controller")
public class StorageController {

    private final StorageService storageService;

    // Warehouse endpoints
    @GetMapping("/warehouses")
    @Operation(summary = "Get all warehouses")
    public List<Warehouse> findAllWarehouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return storageService.findAllWarehouses(PageRequest.of(page, size));
    }

    @GetMapping("/warehouses/{id}")
    @Operation(summary = "Get warehouse by ID")
    public Warehouse findWarehouseById(@PathVariable Long id) {
        return storageService.findWarehouseById(id);
    }

    @PostMapping("/warehouses")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new warehouse")
    public Warehouse createWarehouse(@Valid @RequestBody CreateWarehouseRequest request) {
        var model = Warehouse.builder()
                .name(request.name())
                .build();
        return storageService.saveWarehouse(model, request.locationId());
    }

    @PutMapping("/warehouses/{id}")
    @Operation(summary = "Update warehouse")
    public Warehouse updateWarehouse(@PathVariable Long id, @Valid @RequestBody CreateWarehouseRequest request) {
        var model = Warehouse.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        return storageService.updateWarehouse(id, model);
    }

    @DeleteMapping("/warehouses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete warehouse")
    public void deleteWarehouse(@PathVariable Long id) {
        storageService.deleteWarehouseById(id);
    }

    // Stand endpoints
    @GetMapping("/stands")
    @Operation(summary = "Get all stands")
    public List<Stand> findAllStands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return storageService.findAllStands(PageRequest.of(page, size));
    }

    @GetMapping("/stands/{id}")
    @Operation(summary = "Get stand by ID")
    public Stand findStandById(@PathVariable Long id) {
        return storageService.findStandById(id);
    }

    @PostMapping("/stands")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new stand")
    public Stand createStand(@Valid @RequestBody CreateStandRequest request) {
        var model = Stand.builder()
                .name(request.name())
                .build();
        return storageService.saveStand(model, request.locationId());
    }

    @PutMapping("/stands/{id}")
    @Operation(summary = "Update stand")
    public Stand updateStand(@PathVariable Long id, @Valid @RequestBody CreateStandRequest request) {
        var model = Stand.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        return storageService.updateStand(id, model);
    }

    @DeleteMapping("/stands/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete stand")
    public void deleteStand(@PathVariable Long id) {
        storageService.deleteStandById(id);
    }

    // RelayCabinet endpoints
    @GetMapping("/relay-cabinets")
    @Operation(summary = "Get all relay cabinets")
    public List<RelayCabinet> findAllRelayCabinets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return storageService.findAllRelayCabinets(PageRequest.of(page, size));
    }

    @GetMapping("/relay-cabinets/{id}")
    @Operation(summary = "Get relay cabinet by ID")
    public RelayCabinet findRelayCabinetById(@PathVariable Long id) {
        return storageService.findRelayCabinetById(id);
    }

    @PostMapping("/relay-cabinets")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new relay cabinet")
    public RelayCabinet createRelayCabinet(@Valid @RequestBody CreateRelayCabinetRequest request) {
        var model = RelayCabinet.builder()
                .name(request.name())
                .build();
        return storageService.saveRelayCabinet(model, request.locationId());
    }

    @PutMapping("/relay-cabinets/{id}")
    @Operation(summary = "Update relay cabinet")
    public RelayCabinet updateRelayCabinet(@PathVariable Long id, @Valid @RequestBody CreateRelayCabinetRequest request) {
        var model = RelayCabinet.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        return storageService.updateRelayCabinet(id, model);
    }

    @DeleteMapping("/relay-cabinets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete relay cabinet")
    public void deleteRelayCabinet(@PathVariable Long id) {
        storageService.deleteRelayCabinetById(id);
    }
}
