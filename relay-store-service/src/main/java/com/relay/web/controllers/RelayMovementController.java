package com.relay.web.controllers;

import com.relay.core.service.RelayMovementService;
import com.relay.web.dto.history.CreateRelayMovementRequest;
import com.relay.web.dto.history.GetAllRelayMovementsResponse;
import com.relay.web.mappers.RelayMovementResponseMapper;
import com.relay.web.model.history.RelayMovementResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Relay movement controller")
public class RelayMovementController {

    private final RelayMovementService relayMovementService;
    private final RelayMovementResponseMapper relayMovementResponseMapper;

    @GetMapping("/relay-movements")
    @Operation(summary = "Get all relay movements")
    public GetAllRelayMovementsResponse findAllRelayMovements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var relayMovements = relayMovementService.findAll(PageRequest.of(page, size));
        var relayMovementResponses = relayMovementResponseMapper.mapRelayMovementsToResponse(relayMovements);
        return new GetAllRelayMovementsResponse(relayMovementResponses);
    }

    @GetMapping("/relay-movements/{id}")
    @Operation(summary = "Get relay movement by ID")
    public RelayMovementResponse findRelayMovementById(@PathVariable Long id) {
        var relayMovement = relayMovementService.findById(id);
        return relayMovementResponseMapper.mapRelayMovementToResponse(relayMovement);
    }

    @GetMapping("/relay-movements/relay/{relayId}")
    @Operation(summary = "Get relay movements by relay ID")
    public GetAllRelayMovementsResponse findRelayMovementsByRelayId(
            @PathVariable Long relayId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var relayMovements = relayMovementService.findByRelayId(relayId, PageRequest.of(page, size));
        var relayMovementResponses = relayMovementResponseMapper.mapRelayMovementsToResponse(relayMovements);
        return new GetAllRelayMovementsResponse(relayMovementResponses);
    }

    @PostMapping("/relay-movements")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a relay movement")
    public RelayMovementResponse createRelayMovement(@Valid @RequestBody CreateRelayMovementRequest request) {
        var savedRelayMovement = relayMovementService.save(
                request.relayId(),
                request.fromStorageId(),
                request.toStorageId()
        );
        return relayMovementResponseMapper.mapRelayMovementToResponse(savedRelayMovement);
    }

    @DeleteMapping("/relay-movements/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete relay movement record")
    public void deleteRelayMovement(@PathVariable Long id) {
        relayMovementService.deleteById(id);
    }
}
