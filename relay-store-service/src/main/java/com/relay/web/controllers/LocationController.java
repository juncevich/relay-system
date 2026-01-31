package com.relay.web.controllers;

import com.relay.core.model.location.Crossing;
import com.relay.core.model.location.Station;
import com.relay.core.model.location.TrackPoint;
import com.relay.core.service.LocationService;
import com.relay.web.dto.location.CreateCrossingRequest;
import com.relay.web.dto.location.CreateStationRequest;
import com.relay.web.dto.location.CreateTrackPointRequest;
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
@Tag(name = "Location controller")
public class LocationController {

    private final LocationService locationService;

    // Station endpoints
    @GetMapping("/stations")
    @Operation(summary = "Get all stations")
    public List<Station> findAllStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return locationService.findAllStations(PageRequest.of(page, size));
    }

    @GetMapping("/stations/{id}")
    @Operation(summary = "Get station by ID")
    public ResponseEntity<Station> findStationById(@PathVariable Long id) {
        return locationService.findStationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/stations")
    @Operation(summary = "Create a new station")
    public ResponseEntity<Station> createStation(@Valid @RequestBody CreateStationRequest request) {
        var model = Station.builder()
                .name(request.name())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveStation(model));
    }

    @PutMapping("/stations/{id}")
    @Operation(summary = "Update station")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @Valid @RequestBody CreateStationRequest request) {
        var model = Station.builder()
                .name(request.name())
                .build();
        return ResponseEntity.ok(locationService.updateStation(id, model));
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
    public List<TrackPoint> findAllTrackPoints(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return locationService.findAllTrackPoints(PageRequest.of(page, size));
    }

    @GetMapping("/track-points/{id}")
    @Operation(summary = "Get track point by ID")
    public ResponseEntity<TrackPoint> findTrackPointById(@PathVariable Long id) {
        return locationService.findTrackPointById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/track-points")
    @Operation(summary = "Create a new track point")
    public ResponseEntity<TrackPoint> createTrackPoint(@Valid @RequestBody CreateTrackPointRequest request) {
        var model = TrackPoint.builder()
                .name(request.name())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveTrackPoint(model));
    }

    @PutMapping("/track-points/{id}")
    @Operation(summary = "Update track point")
    public ResponseEntity<TrackPoint> updateTrackPoint(@PathVariable Long id, @Valid @RequestBody CreateTrackPointRequest request) {
        var model = TrackPoint.builder()
                .name(request.name())
                .build();
        return ResponseEntity.ok(locationService.updateTrackPoint(id, model));
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
    public List<Crossing> findAllCrossings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return locationService.findAllCrossings(PageRequest.of(page, size));
    }

    @GetMapping("/crossings/{id}")
    @Operation(summary = "Get crossing by ID")
    public ResponseEntity<Crossing> findCrossingById(@PathVariable Long id) {
        return locationService.findCrossingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crossings")
    @Operation(summary = "Create a new crossing")
    public ResponseEntity<Crossing> createCrossing(@Valid @RequestBody CreateCrossingRequest request) {
        var model = Crossing.builder()
                .name(request.name())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.saveCrossing(model));
    }

    @PutMapping("/crossings/{id}")
    @Operation(summary = "Update crossing")
    public ResponseEntity<Crossing> updateCrossing(@PathVariable Long id, @Valid @RequestBody CreateCrossingRequest request) {
        var model = Crossing.builder()
                .name(request.name())
                .build();
        return ResponseEntity.ok(locationService.updateCrossing(id, model));
    }

    @DeleteMapping("/crossings/{id}")
    @Operation(summary = "Delete crossing")
    public ResponseEntity<Void> deleteCrossing(@PathVariable Long id) {
        locationService.deleteCrossingById(id);
        return ResponseEntity.noContent().build();
    }
}
