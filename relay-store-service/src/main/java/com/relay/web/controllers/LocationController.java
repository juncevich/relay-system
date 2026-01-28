package com.relay.web.controllers;

import com.relay.core.model.location.CrossingModel;
import com.relay.core.model.location.StationModel;
import com.relay.core.model.location.TrackPointModel;
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
    public List<StationModel> findAllStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return locationService.findAllStations(PageRequest.of(page, size));
    }

    @GetMapping("/stations/{id}")
    @Operation(summary = "Get station by ID")
    public StationModel findStationById(@PathVariable Long id) {
        return locationService.findStationById(id);
    }

    @PostMapping("/stations")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new station")
    public StationModel createStation(@Valid @RequestBody CreateStationRequest request) {
        var model = StationModel.builder()
                .name(request.name())
                .build();
        return locationService.saveStation(model);
    }

    @PutMapping("/stations/{id}")
    @Operation(summary = "Update station")
    public StationModel updateStation(@PathVariable Long id, @Valid @RequestBody CreateStationRequest request) {
        var model = StationModel.builder()
                .name(request.name())
                .build();
        return locationService.updateStation(id, model);
    }

    @DeleteMapping("/stations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete station")
    public void deleteStation(@PathVariable Long id) {
        locationService.deleteStationById(id);
    }

    // TrackPoint endpoints
    @GetMapping("/track-points")
    @Operation(summary = "Get all track points")
    public List<TrackPointModel> findAllTrackPoints(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return locationService.findAllTrackPoints(PageRequest.of(page, size));
    }

    @GetMapping("/track-points/{id}")
    @Operation(summary = "Get track point by ID")
    public TrackPointModel findTrackPointById(@PathVariable Long id) {
        return locationService.findTrackPointById(id);
    }

    @PostMapping("/track-points")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new track point")
    public TrackPointModel createTrackPoint(@Valid @RequestBody CreateTrackPointRequest request) {
        var model = TrackPointModel.builder()
                .name(request.name())
                .build();
        return locationService.saveTrackPoint(model);
    }

    @PutMapping("/track-points/{id}")
    @Operation(summary = "Update track point")
    public TrackPointModel updateTrackPoint(@PathVariable Long id, @Valid @RequestBody CreateTrackPointRequest request) {
        var model = TrackPointModel.builder()
                .name(request.name())
                .build();
        return locationService.updateTrackPoint(id, model);
    }

    @DeleteMapping("/track-points/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete track point")
    public void deleteTrackPoint(@PathVariable Long id) {
        locationService.deleteTrackPointById(id);
    }

    // Crossing endpoints
    @GetMapping("/crossings")
    @Operation(summary = "Get all crossings")
    public List<CrossingModel> findAllCrossings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return locationService.findAllCrossings(PageRequest.of(page, size));
    }

    @GetMapping("/crossings/{id}")
    @Operation(summary = "Get crossing by ID")
    public CrossingModel findCrossingById(@PathVariable Long id) {
        return locationService.findCrossingById(id);
    }

    @PostMapping("/crossings")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new crossing")
    public CrossingModel createCrossing(@Valid @RequestBody CreateCrossingRequest request) {
        var model = CrossingModel.builder()
                .name(request.name())
                .build();
        return locationService.saveCrossing(model);
    }

    @PutMapping("/crossings/{id}")
    @Operation(summary = "Update crossing")
    public CrossingModel updateCrossing(@PathVariable Long id, @Valid @RequestBody CreateCrossingRequest request) {
        var model = CrossingModel.builder()
                .name(request.name())
                .build();
        return locationService.updateCrossing(id, model);
    }

    @DeleteMapping("/crossings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete crossing")
    public void deleteCrossing(@PathVariable Long id) {
        locationService.deleteCrossingById(id);
    }
}
