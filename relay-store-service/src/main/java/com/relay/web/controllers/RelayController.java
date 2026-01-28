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
        return relayService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/relays/{id}")
    @Operation(summary = "Get relay by ID")
    public Relay findRelayById(@PathVariable Long id) {
        return relayService.findById(id);
    }

    @GetMapping("/relays/serial-number/{serialNumber}")
    @Operation(summary = "Find relay by serial number")
    public Relay findBySerialNumber(@PathVariable String serialNumber) {
        return relayService.findBySerialNumber(serialNumber);
    }

    @GetMapping("/relays/by-creation-date")
    @Operation(summary = "Find relays by creation date")
    public List<Relay> findByCreationDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return relayService.findByCreationDate(date, PageRequest.of(page, size));
    }

    @GetMapping("/relays/by-last-check-date")
    @Operation(summary = "Find relays by last check date")
    public List<Relay> findByLastCheckDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return relayService.findByLastCheckDate(date, PageRequest.of(page, size));
    }

    @PostMapping("/relays")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new relay")
    public CreateRelayResponse createRelay(@Valid @RequestBody CreateRelayRequest request) {
        var relayToCreate = Relay.builder()
                .serialNumber(request.serialNumber())
                .createdAt(request.dateOfManufacture())
                .build();

        var createdRelay = relayService.save(relayToCreate, request.storageId());
        return new CreateRelayResponse(
                createdRelay.getSerialNumber(),
                createdRelay.getCreatedAt(),
                createdRelay.getVerificationDate()
        );
    }

    @PutMapping("/relays/{id}")
    @Operation(summary = "Update relay")
    public Relay updateRelay(@PathVariable Long id, @Valid @RequestBody UpdateRelayRequest request) {
        var relay = Relay.builder()
                .serialNumber(request.serialNumber())
                .createdAt(request.dateOfManufacture())
                .verificationDate(request.verificationDate())
                .build();

        return relayService.update(id, relay, request.storageId());
    }

    @DeleteMapping("/relays/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete relay")
    public void deleteRelay(@PathVariable Long id) {
        relayService.deleteById(id);
    }
}
