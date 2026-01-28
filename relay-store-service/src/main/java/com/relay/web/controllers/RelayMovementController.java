package com.relay.web.controllers;

import com.relay.core.model.history.RelayMovementModel;
import com.relay.core.service.RelayMovementService;
import com.relay.web.dto.history.CreateRelayMovementRequest;
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
@Tag(name = "Relay movement controller")
public class RelayMovementController {

    private final RelayMovementService relayMovementService;

    @GetMapping("/relay-movements")
    @Operation(summary = "Get all relay movements")
    public List<RelayMovementModel> findAllRelayMovements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return relayMovementService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/relay-movements/{id}")
    @Operation(summary = "Get relay movement by ID")
    public RelayMovementModel findRelayMovementById(@PathVariable Long id) {
        return relayMovementService.findById(id);
    }

    @GetMapping("/relay-movements/relay/{relayId}")
    @Operation(summary = "Get relay movements by relay ID")
    public List<RelayMovementModel> findRelayMovementsByRelayId(
            @PathVariable Long relayId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return relayMovementService.findByRelayId(relayId, PageRequest.of(page, size));
    }

    @PostMapping("/relay-movements")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a relay movement")
    public RelayMovementModel createRelayMovement(@Valid @RequestBody CreateRelayMovementRequest request) {
        return relayMovementService.save(
                request.relayId(),
                request.fromStorageId(),
                request.toStorageId()
        );
    }

    @DeleteMapping("/relay-movements/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete relay movement record")
    public void deleteRelayMovement(@PathVariable Long id) {
        relayMovementService.deleteById(id);
    }
}
