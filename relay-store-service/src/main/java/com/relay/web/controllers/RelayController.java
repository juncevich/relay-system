package com.relay.web.controllers;

import com.relay.core.service.RelayService;
import com.relay.web.dto.CreateRelayRequest;
import com.relay.web.dto.CreateRelayResponse;
import com.relay.web.model.Relay;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Relays controller")
public class RelayController {

    /**
     * Relay service
     */
    private final RelayService relayService;

    //TODO Should be avoid to get all relays.
    @GetMapping(value = "/relays")
    @Operation(
            description = "Get all relays description",
            summary = "Get all relays summary"
    )
    public List<Relay> findAllRelays() {
        return relayService.findAll(PageRequest.of(0, 10));
    }

    //
    // /**
    // * Delete current relay
    // *
    // * @param id id relay to delete
    // */
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // @DeleteMapping("/relay/{id}")
    // public void deleteRelay(@PathVariable BigInteger id) {
    //
    // relayService.deleteById(id);
    // }

    /**
     * Create relay
     *
     * @param request relay to create
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/relay")
    public CreateRelayResponse createRelay(@Valid @RequestBody CreateRelayRequest request) {
        var relayToCreate = Relay.builder()
                .serialNumber(request.serialNumber())
                .createdAt(request.dateOfManufacture())
                .build();

        var createdRelay = relayService.save(relayToCreate);
        return new CreateRelayResponse(
                createdRelay.getSerialNumber(),
                createdRelay.getCreatedAt(),
                createdRelay.getVerificationDate()
        );
    }

    // /**
    // * Finding relay by verification date
    // *
    // * @param verificationDate {@link Relay#getVerificationDate()}
    // * @return Page of {@link Relay}
    // */
    // @ResponseStatus(HttpStatus.OK)
    // @PostMapping("/relay/verificationDate")
    // public Page<Relay> findByVerificationDate(@RequestBody LocalDate verificationDate) {
    //
    // return relayService.findByVerificationDate(verificationDate);
    // }
    //

    /**
     * Find relay by id
     *
     * @param id {@link Relay#getSerialNumber()}
     * @return {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/relay/{id}")
    public Relay findRelayById(@PathVariable Long id) {

        return relayService.findOne(id);
    }
    //
    // /**
    // * Finding relay by verification date
    // *
    // * @param date {@link Relay#getDateOfManufacture()}
    // * @return Page of {@link Relay}
    // */
    // @ResponseStatus(HttpStatus.OK)
    // @GetMapping("/relay/dateOfManufacture")
    // public Page<Relay> findByDateOfManufacture(@PathParam("date") String date) {
    //
    // return relayService.findByDateOfManufacture(LocalDate.parse(date));
    // }
    //
    // /**
    // * Finding relay before date of manufacture
    // *
    // * @param before The date before {@link Relay#getDateOfManufacture()}
    // * @return Page of {@link Relay}
    // */
    // @ResponseStatus(HttpStatus.OK)
    // @GetMapping(value = "/relay/dateOfManufacture",
    // params = "before")
    // public Page<Relay> findByDateOfManufactureBefore(@PathParam("before") String before) {
    //
    // return relayService.findByDateOfManufactureBefore(LocalDate.parse(before));
    // }
    //

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/relay/serialNumber",
            params = "serialNumber")
    public Relay findBySerialNumber(@PathParam("serialNumber") String serialNumber) {

        return relayService.findBySerialNumber(serialNumber);
    }
    //
    // /**
    // * Finding relay after date of manufacture
    // *
    // * @param after The date before {@link Relay#getDateOfManufacture()}
    // * @return Page of {@link Relay}
    // */
    // @ResponseStatus(HttpStatus.OK)
    // @GetMapping(value = "/relay/dateOfManufacture",
    // params = "after")
    // public Page<Relay> findByDateOfManufactureAfter(@PathParam("after") String after) {
    //
    // return relayService.findByDateOfManufactureAfter(LocalDate.parse(after));
    // }

}
