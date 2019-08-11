package com.relay.controllers;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.relay.model.Relay;
import com.relay.service.RelayService;

@RestController
public class RelayController {

    /**
     * Relay service
     */
    private final RelayService relayService;

    /**
     * Init beans
     *
     * @param relayService
     *            relay service
     */
    public RelayController(RelayService relayService) {

        this.relayService = relayService;
    }

    /**
     * Retrieve all relays
     * 
     * @return list with all relays
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/relays")
    public Page<Relay> retrieveAllRelays() {

        return relayService.findAll();
    }

    /**
     * Delete current relay
     *
     * @param id
     *            id relay to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/relay/{id}")
    public void deleteRelay(@PathVariable BigInteger id) {

        relayService.deleteById(id);
    }

    /**
     * Create relay
     * 
     * @param relay
     *            relay to create
     * @return created {@link Relay}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/relay")
    public Relay createRelay(@Valid @RequestBody Relay relay) {

        return relayService.save(relay);
    }

    /**
     * Finding relay by verification date
     * 
     * @param verificationDate
     *            {@link Relay#verificationDate}
     * @return Page of {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/relay/verificationDate")
    public Page<Relay> findByVerificationDate(@RequestBody LocalDate verificationDate) {

        return relayService.findByVerificationDate(verificationDate);
    }

    /**
     * Find relay by id
     * 
     * @param id
     *            {@link Relay#id}
     * @return {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/relay/{id}")
    public Relay findRelayById(@PathVariable BigInteger id) {

        return relayService.findOne(id).orElse(null);
    }

    /**
     * Finding relay by verification date
     *
     * @param date
     *            {@link Relay#dateOfManufacture}
     * @return Page of {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/relay/dateOfManufacture")
    public Page<Relay> findByDateOfManufacture(@PathParam("date") String date) {

        return relayService.findByDateOfManufacture(LocalDate.parse(date));
    }

    /**
     * Finding relay before date of manufacture
     * 
     * @param before
     *            The date before {@link Relay#dateOfManufacture}
     * @return Page of {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/relay/dateOfManufacture",
            params = "before")
    public Page<Relay> findByDateOfManufactureBefore(@PathParam("before") String before) {

        return relayService.findByDateOfManufactureBefore(LocalDate.parse(before));
    }

    /**
     * Find relay by serial number
     * 
     * @param serialNumber
     *            {@link Relay#serialNumber}
     * @return {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/relay/serialNumber",
            params = "serialNumber")
    public Relay findBySerialNumber(@PathParam("serialNumber") String serialNumber) {

        return relayService.findBySerialNumber(serialNumber);
    }

    /**
     * Finding relay after date of manufacture
     *
     * @param after
     *            The date before {@link Relay#dateOfManufacture}
     * @return Page of {@link Relay}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/relay/dateOfManufacture",
            params = "after")
    public Page<Relay> findByDateOfManufactureAfter(@PathParam("after") String after) {

        return relayService.findByDateOfManufactureAfter(LocalDate.parse(after));
    }

}