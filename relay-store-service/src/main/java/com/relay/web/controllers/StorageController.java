package com.relay.web.controllers;

import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.core.service.StorageService;
import com.relay.web.dto.storage.*;
import com.relay.web.mappers.StorageResponseMapper;
import com.relay.web.model.storage.RelayCabinetResponse;
import com.relay.web.model.storage.StandResponse;
import com.relay.web.model.storage.WarehouseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Storage controller")
public class StorageController {

    private final StorageService storageService;
    private final StorageResponseMapper storageResponseMapper;

    // Warehouse endpoints
    @GetMapping("/warehouses")
    @Operation(summary = "Get all warehouses")
    public GetAllWarehousesResponse findAllWarehouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var warehouses = storageService.findAllWarehouses(PageRequest.of(page, size));
        var warehouseResponses = storageResponseMapper.mapWarehousesToResponse(warehouses);
        return new GetAllWarehousesResponse(warehouseResponses);
    }

    @GetMapping("/warehouses/{id}")
    @Operation(summary = "Get warehouse by ID")
    public ResponseEntity<WarehouseResponse> findWarehouseById(@PathVariable Long id) {
        return storageService.findWarehouseById(id)
                .map(storageResponseMapper::mapWarehouseToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/warehouses")
    @Operation(summary = "Create a new warehouse")
    public ResponseEntity<WarehouseResponse> createWarehouse(@Valid @RequestBody CreateWarehouseRequest request) {
        var model = Warehouse.builder()
                .name(request.name())
                .build();
        var savedWarehouse = storageService.saveWarehouse(model, request.locationId());
        var response = storageResponseMapper.mapWarehouseToResponse(savedWarehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/warehouses/{id}")
    @Operation(summary = "Update warehouse")
    public ResponseEntity<WarehouseResponse> updateWarehouse(@PathVariable Long id, @Valid @RequestBody CreateWarehouseRequest request) {
        var model = Warehouse.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        var updatedWarehouse = storageService.updateWarehouse(id, model);
        var response = storageResponseMapper.mapWarehouseToResponse(updatedWarehouse);
        return ResponseEntity.ok(response);
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
    public GetAllStandsResponse findAllStands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var stands = storageService.findAllStands(PageRequest.of(page, size));
        var standResponses = storageResponseMapper.mapStandsToResponse(stands);
        return new GetAllStandsResponse(standResponses);
    }

    @GetMapping("/stands/{id}")
    @Operation(summary = "Get stand by ID")
    public ResponseEntity<StandResponse> findStandById(@PathVariable Long id) {
        return storageService.findStandById(id)
                .map(storageResponseMapper::mapStandToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/stands")
    @Operation(summary = "Create a new stand")
    public ResponseEntity<StandResponse> createStand(@Valid @RequestBody CreateStandRequest request) {
        var model = Stand.builder()
                .name(request.name())
                .build();
        var savedStand = storageService.saveStand(model, request.locationId());
        var response = storageResponseMapper.mapStandToResponse(savedStand);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/stands/{id}")
    @Operation(summary = "Update stand")
    public ResponseEntity<StandResponse> updateStand(@PathVariable Long id, @Valid @RequestBody CreateStandRequest request) {
        var model = Stand.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        var updatedStand = storageService.updateStand(id, model);
        var response = storageResponseMapper.mapStandToResponse(updatedStand);
        return ResponseEntity.ok(response);
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
    public GetAllRelayCabinetsResponse findAllRelayCabinets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var relayCabinets = storageService.findAllRelayCabinets(PageRequest.of(page, size));
        var relayCabinetResponses = storageResponseMapper.mapRelayCabinetsToResponse(relayCabinets);
        return new GetAllRelayCabinetsResponse(relayCabinetResponses);
    }

    @GetMapping("/relay-cabinets/{id}")
    @Operation(summary = "Get relay cabinet by ID")
    public ResponseEntity<RelayCabinetResponse> findRelayCabinetById(@PathVariable Long id) {
        return storageService.findRelayCabinetById(id)
                .map(storageResponseMapper::mapRelayCabinetToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/relay-cabinets")
    @Operation(summary = "Create a new relay cabinet")
    public ResponseEntity<RelayCabinetResponse> createRelayCabinet(@Valid @RequestBody CreateRelayCabinetRequest request) {
        var model = RelayCabinet.builder()
                .name(request.name())
                .build();
        var savedRelayCabinet = storageService.saveRelayCabinet(model, request.locationId());
        var response = storageResponseMapper.mapRelayCabinetToResponse(savedRelayCabinet);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/relay-cabinets/{id}")
    @Operation(summary = "Update relay cabinet")
    public ResponseEntity<RelayCabinetResponse> updateRelayCabinet(@PathVariable Long id, @Valid @RequestBody CreateRelayCabinetRequest request) {
        var model = RelayCabinet.builder()
                .name(request.name())
                .locationId(request.locationId())
                .build();
        var updatedRelayCabinet = storageService.updateRelayCabinet(id, model);
        var response = storageResponseMapper.mapRelayCabinetToResponse(updatedRelayCabinet);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/relay-cabinets/{id}")
    @Operation(summary = "Delete relay cabinet")
    public ResponseEntity<Void> deleteRelayCabinet(@PathVariable Long id) {
        storageService.deleteRelayCabinetById(id);
        return ResponseEntity.noContent().build();
    }
}
