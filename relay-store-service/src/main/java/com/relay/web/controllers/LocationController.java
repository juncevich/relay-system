package com.relay.web.controllers;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.core.service.LocationService;
import com.relay.web.dto.location.*;
import com.relay.web.mappers.LocationResponseMapper;
import com.relay.web.model.location.CrossingResponse;
import com.relay.web.model.location.StationResponse;
import com.relay.web.model.location.TrackPointResponse;
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
@Tag(name = "Location controller")
public class LocationController {

    private final LocationService locationService;
    private final LocationResponseMapper locationResponseMapper;

    // Station endpoints
    @GetMapping("/stations")
    @Operation(summary = "Get all stations")
    public GetAllStationsResponse findAllStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = locationService.findAllStations(PageRequest.of(page, size));
        var stationResponses = locationResponseMapper.mapStationsToResponse(pageResult.getContent());
        return new GetAllStationsResponse(stationResponses, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @GetMapping("/stations/{id}")
    @Operation(summary = "Get station by ID")
    public ResponseEntity<StationResponse> findStationById(@PathVariable Long id) {
        return locationService.findStationById(id)
                .map(locationResponseMapper::mapStationToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/stations")
    @Operation(summary = "Create a new station")
    public ResponseEntity<StationResponse> createStation(@Valid @RequestBody CreateStationRequest request) {
        var model = Station.builder()
                .name(request.name())
                .build();
        var savedStation = locationService.saveStation(model);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(locationResponseMapper.mapStationToResponse(savedStation));
    }

    @PutMapping("/stations/{id}")
    @Operation(summary = "Update station")
    public ResponseEntity<StationResponse> updateStation(@PathVariable Long id, @Valid @RequestBody CreateStationRequest request) {
        var model = Station.builder()
                .name(request.name())
                .build();
        var updatedStation = locationService.updateStation(id, model);
        return ResponseEntity.ok(locationResponseMapper.mapStationToResponse(updatedStation));
    }

    @DeleteMapping("/stations/{id}")
    @Operation(summary = "Delete station")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        locationService.deleteStationById(id);
        return ResponseEntity.noContent().build();
    }

    // TrackPoint endpoints
    @GetMapping("/track-points")
    @Operation(summary = "Get all track points")
    public GetAllTrackPointsResponse findAllTrackPoints(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = locationService.findAllTrackPoints(PageRequest.of(page, size));
        var trackPointResponses = locationResponseMapper.mapTrackPointsToResponse(pageResult.getContent());
        return new GetAllTrackPointsResponse(trackPointResponses, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @GetMapping("/track-points/{id}")
    @Operation(summary = "Get track point by ID")
    public ResponseEntity<TrackPointResponse> findTrackPointById(@PathVariable Long id) {
        return locationService.findTrackPointById(id)
                .map(locationResponseMapper::mapTrackPointToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/track-points")
    @Operation(summary = "Create a new track point")
    public ResponseEntity<TrackPointResponse> createTrackPoint(@Valid @RequestBody CreateTrackPointRequest request) {
        var model = TrackPoint.builder()
                .name(request.name())
                .build();
        var savedTrackPoint = locationService.saveTrackPoint(model);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(locationResponseMapper.mapTrackPointToResponse(savedTrackPoint));
    }

    @PutMapping("/track-points/{id}")
    @Operation(summary = "Update track point")
    public ResponseEntity<TrackPointResponse> updateTrackPoint(@PathVariable Long id, @Valid @RequestBody CreateTrackPointRequest request) {
        var model = TrackPoint.builder()
                .name(request.name())
                .build();
        var updatedTrackPoint = locationService.updateTrackPoint(id, model);
        return ResponseEntity.ok(locationResponseMapper.mapTrackPointToResponse(updatedTrackPoint));
    }

    @DeleteMapping("/track-points/{id}")
    @Operation(summary = "Delete track point")
    public ResponseEntity<Void> deleteTrackPoint(@PathVariable Long id) {
        locationService.deleteTrackPointById(id);
        return ResponseEntity.noContent().build();
    }

    // Crossing endpoints
    @GetMapping("/crossings")
    @Operation(summary = "Get all crossings")
    public GetAllCrossingsResponse findAllCrossings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = locationService.findAllCrossings(PageRequest.of(page, size));
        var crossingResponses = locationResponseMapper.mapCrossingsToResponse(pageResult.getContent());
        return new GetAllCrossingsResponse(crossingResponses, pageResult.getTotalElements(),
                pageResult.getTotalPages(), pageResult.getSize(), pageResult.getNumber());
    }

    @GetMapping("/crossings/{id}")
    @Operation(summary = "Get crossing by ID")
    public ResponseEntity<CrossingResponse> findCrossingById(@PathVariable Long id) {
        return locationService.findCrossingById(id)
                .map(locationResponseMapper::mapCrossingToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crossings")
    @Operation(summary = "Create a new crossing")
    public ResponseEntity<CrossingResponse> createCrossing(@Valid @RequestBody CreateCrossingRequest request) {
        var model = Crossing.builder()
                .name(request.name())
                .build();
        var savedCrossing = locationService.saveCrossing(model);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(locationResponseMapper.mapCrossingToResponse(savedCrossing));
    }

    @PutMapping("/crossings/{id}")
    @Operation(summary = "Update crossing")
    public ResponseEntity<CrossingResponse> updateCrossing(@PathVariable Long id, @Valid @RequestBody CreateCrossingRequest request) {
        var model = Crossing.builder()
                .name(request.name())
                .build();
        var updatedCrossing = locationService.updateCrossing(id, model);
        return ResponseEntity.ok(locationResponseMapper.mapCrossingToResponse(updatedCrossing));
    }

    @DeleteMapping("/crossings/{id}")
    @Operation(summary = "Delete crossing")
    public ResponseEntity<Void> deleteCrossing(@PathVariable Long id) {
        locationService.deleteCrossingById(id);
        return ResponseEntity.noContent().build();
    }
}
