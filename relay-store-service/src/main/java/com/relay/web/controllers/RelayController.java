package com.relay.web.controllers;

import com.relay.core.service.RelayService;
import com.relay.web.dto.CreateRelayRequest;
import com.relay.web.dto.CreateRelayResponse;
import com.relay.web.dto.UpdateRelayRequest;
import com.relay.web.model.Relay;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Relays controller")
public class RelayController {

    private final RelayService relayService;

    @GetMapping("/relays")
    @Operation(summary = "Get all relays")
    public List<Relay> findAllRelays(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var coreModels = relayService.findAll(PageRequest.of(page, size));
        return coreModels.stream()
                .map(this::convertToWebModel)
                .toList();
    }

    @GetMapping("/relays/{id}")
    @Operation(summary = "Get relay by ID")
    public ResponseEntity<Relay> findRelayById(@PathVariable Long id) {
        return relayService.findById(id)
                .map(this::convertToWebModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/relays/serial-number/{serialNumber}")
    @Operation(summary = "Find relay by serial number")
    public ResponseEntity<Relay> findBySerialNumber(@PathVariable String serialNumber) {
        return relayService.findBySerialNumber(serialNumber)
                .map(this::convertToWebModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/relays/by-creation-date")
    @Operation(summary = "Find relays by creation date")
    public List<Relay> findByCreationDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var coreModels = relayService.findByCreationDate(date, PageRequest.of(page, size));
        return coreModels.stream()
                .map(this::convertToWebModel)
                .toList();
    }

    @GetMapping("/relays/by-last-check-date")
    @Operation(summary = "Find relays by last check date")
    public List<Relay> findByLastCheckDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var coreModels = relayService.findByLastCheckDate(date, PageRequest.of(page, size));
        return coreModels.stream()
                .map(this::convertToWebModel)
                .toList();
    }

    @PostMapping("/relays")
    @Operation(summary = "Create a new relay")
    public ResponseEntity<CreateRelayResponse> createRelay(@Valid @RequestBody CreateRelayRequest request) {
        // Convert to core model
        var coreModel = new com.relay.core.model.Relay(
                null,  // id will be generated
                request.serialNumber(),
                null,  // relayType - will be set by service
                request.dateOfManufacture(),
                null,  // lastCheckDate
                0,     // placeNumber
                request.storageId(),
                null   // shelfId
        );

        var savedModel = relayService.save(coreModel, request.storageId());
        var response = new CreateRelayResponse(
                savedModel.serialNumber(),
                savedModel.createdAt(),
                savedModel.lastCheckDate()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/relays/{id}")
    @Operation(summary = "Update relay")
    public ResponseEntity<Relay> updateRelay(@PathVariable Long id, @Valid @RequestBody UpdateRelayRequest request) {
        // Convert to core model
        var coreModel = new com.relay.core.model.Relay(
                id,
                request.serialNumber(),
                null,  // relayType - will be preserved from existing
                request.dateOfManufacture(),
                request.verificationDate(),
                0,     // placeNumber
                request.storageId(),
                null   // shelfId
        );

        var updated = relayService.update(id, coreModel, request.storageId());
        return ResponseEntity.ok(convertToWebModel(updated));
    }

    @DeleteMapping("/relays/{id}")
    @Operation(summary = "Delete relay")
    public ResponseEntity<Void> deleteRelay(@PathVariable Long id) {
        relayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Relay convertToWebModel(com.relay.core.model.Relay core) {
        return Relay.builder()
                .serialNumber(core.serialNumber())
                .createdAt(core.createdAt())
                .verificationDate(core.lastCheckDate())
                .build();
    }
}
