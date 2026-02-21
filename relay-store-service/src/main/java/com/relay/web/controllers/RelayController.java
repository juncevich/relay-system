package com.relay.web.controllers;

import com.relay.core.service.RelayService;
import com.relay.web.dto.CreateRelayRequest;
import com.relay.web.dto.CreateRelayResponse;
import com.relay.web.dto.GetAllRelaysResponse;
import com.relay.web.dto.UpdateRelayRequest;
import com.relay.web.mappers.RelayResponseMapper;
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

@RestController
@RequiredArgsConstructor
@Tag(name = "Relays controller")
public class RelayController {

    private final RelayService relayService;
    private final RelayResponseMapper relayResponseMapper;

    @GetMapping("/relays")
    @Operation(summary = "Get all relays")
    public GetAllRelaysResponse findAllRelays(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = relayService.findAll(PageRequest.of(page, size));
        var relays = relayResponseMapper.mapToResponseList(pageResult.getContent());
        return new GetAllRelaysResponse(relays, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @GetMapping("/relays/{id}")
    @Operation(summary = "Get relay by ID")
    public ResponseEntity<Relay> findRelayById(@PathVariable Long id) {
        return relayService.findById(id)
                .map(relayResponseMapper::mapToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/relays/serial-number/{serialNumber}")
    @Operation(summary = "Find relay by serial number")
    public ResponseEntity<Relay> findBySerialNumber(@PathVariable String serialNumber) {
        return relayService.findBySerialNumber(serialNumber)
                .map(relayResponseMapper::mapToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/relays/by-creation-date")
    @Operation(summary = "Find relays by creation date")
    public GetAllRelaysResponse findByCreationDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = relayService.findByCreationDate(date, PageRequest.of(page, size));
        var relays = relayResponseMapper.mapToResponseList(pageResult.getContent());
        return new GetAllRelaysResponse(relays, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @GetMapping("/relays/by-last-check-date")
    @Operation(summary = "Find relays by last check date")
    public GetAllRelaysResponse findByLastCheckDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = relayService.findByLastCheckDate(date, PageRequest.of(page, size));
        var relays = relayResponseMapper.mapToResponseList(pageResult.getContent());
        return new GetAllRelaysResponse(relays, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @PostMapping("/relays")
    @Operation(summary = "Create a new relay")
    public ResponseEntity<CreateRelayResponse> createRelay(@Valid @RequestBody CreateRelayRequest request) {
        var coreModel = new com.relay.core.model.Relay(
                null,
                request.serialNumber(),
                null,
                request.dateOfManufacture(),
                null,
                0,
                request.storageId(),
                null
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
        var coreModel = new com.relay.core.model.Relay(
                id,
                request.serialNumber(),
                null,
                request.dateOfManufacture(),
                request.verificationDate(),
                0,
                request.storageId(),
                null
        );

        var updated = relayService.update(id, coreModel, request.storageId());
        return ResponseEntity.ok(relayResponseMapper.mapToResponse(updated));
    }

    @DeleteMapping("/relays/{id}")
    @Operation(summary = "Delete relay")
    public ResponseEntity<Void> deleteRelay(@PathVariable Long id) {
        relayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
