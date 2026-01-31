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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable Long id) {
        return storageService.findWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/warehouses")
    @Operation(summary = "Create a new warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody CreateWarehouseRequest request) {
        var model = Warehouse.builder()
                .name(request.name())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(storageService.saveWarehouse(model, request.locationId()));
    }

    @PutMapping("/warehouses/{id}")
    @Operation(summary = "Update warehouse")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @Valid @RequestBody CreateWarehouseRequest request) {
        var model = Warehouse.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        return ResponseEntity.ok(storageService.updateWarehouse(id, model));
    }

    @DeleteMapping("/warehouses/{id}")
    @Operation(summary = "Delete warehouse")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        storageService.deleteWarehouseById(id);
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<Stand> findStandById(@PathVariable Long id) {
        return storageService.findStandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/stands")
    @Operation(summary = "Create a new stand")
    public ResponseEntity<Stand> createStand(@Valid @RequestBody CreateStandRequest request) {
        var model = Stand.builder()
                .name(request.name())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(storageService.saveStand(model, request.locationId()));
    }

    @PutMapping("/stands/{id}")
    @Operation(summary = "Update stand")
    public ResponseEntity<Stand> updateStand(@PathVariable Long id, @Valid @RequestBody CreateStandRequest request) {
        var model = Stand.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        return ResponseEntity.ok(storageService.updateStand(id, model));
    }

    @DeleteMapping("/stands/{id}")
    @Operation(summary = "Delete stand")
    public ResponseEntity<Void> deleteStand(@PathVariable Long id) {
        storageService.deleteStandById(id);
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<RelayCabinet> findRelayCabinetById(@PathVariable Long id) {
        return storageService.findRelayCabinetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/relay-cabinets")
    @Operation(summary = "Create a new relay cabinet")
    public ResponseEntity<RelayCabinet> createRelayCabinet(@Valid @RequestBody CreateRelayCabinetRequest request) {
        var model = RelayCabinet.builder()
                .name(request.name())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(storageService.saveRelayCabinet(model, request.locationId()));
    }

    @PutMapping("/relay-cabinets/{id}")
    @Operation(summary = "Update relay cabinet")
    public ResponseEntity<RelayCabinet> updateRelayCabinet(@PathVariable Long id, @Valid @RequestBody CreateRelayCabinetRequest request) {
        var model = RelayCabinet.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        return ResponseEntity.ok(storageService.updateRelayCabinet(id, model));
    }

    @DeleteMapping("/relay-cabinets/{id}")
    @Operation(summary = "Delete relay cabinet")
    public ResponseEntity<Void> deleteRelayCabinet(@PathVariable Long id) {
        storageService.deleteRelayCabinetById(id);
        return ResponseEntity.noContent().build();
    }
}
