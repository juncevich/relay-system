package com.relay.util;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.relay.model.Relay;
import com.relay.model.places.Station;
import com.relay.model.statives.Stativ;
import com.relay.service.RelayService;
import com.relay.service.StationService;
import com.relay.service.StativService;

@Component
public class TestUtils {

    @Autowired
    private RelayService relayService;

    @Autowired
    private StationService stationService;

    private StativService stativService;

    public void generateRelay(Integer amount) {

        Stream.generate(() -> {
            Relay relay = new Relay();
            relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
            relay.setVerificationDate(LocalDate.of(2018, 6, 25));
            relay.setSerialNumber(String.valueOf(new Random().nextInt(20) + 10000));
            return relay;
        }).limit(amount).forEach(relay -> relayService.save(relay));
    }

    public void generateRelayToStation(Integer amount, Station station) {

        Stream.generate(() -> {
            Relay relay = new Relay();
            relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
            relay.setVerificationDate(LocalDate.of(2018, 6, 25));
            relay.setSerialNumber(String.valueOf(new Random().nextInt(20) + 10000));
            station.getRelay().add(relay);
            return relay;
        }).limit(amount).forEach(relay -> {
            relayService.save(relay);
            stationService.save(station);
        });
    }

    public void generateRelayToStativ(Integer amount, Stativ stativ) {

        Stream.generate(() -> {
            Relay relay = new Relay();
            relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
            relay.setVerificationDate(LocalDate.of(2018, 6, 25));
            relay.setSerialNumber(String.valueOf(new Random().nextInt(20) + 10000));
            stativ.addRelay(relay);
            return relay;
        }).limit(amount).forEach(relay -> {
            relayService.save(relay);
            stativService.save(stativ);
        });
    }

    public void generateEmptyStation(Integer amount) {

        Stream.generate(() -> new Station(RandomStringUtils.randomAlphabetic(6))).limit(amount)
                .forEach(station -> stationService.save(station));
    }

    // public void generateStationWithRelayAndStatives(Integer amount) {
    //
    // Stream.generate(() -> new Station(RandomStringUtils.randomAlphabetic(6))).limit(amount)
    // .flatMap(station -> Stream.generate(() -> {
    // Stativ stativ = new Stativ(new Random().nextInt(20));
    // station.getStatives().add(stativ);
    // return stativ;
    // }).limit(5)).flatMap(stativ -> generateRelayToStativ(5, stativ));
    // }
}
