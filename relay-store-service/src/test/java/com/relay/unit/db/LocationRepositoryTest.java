package com.relay.unit.db;

import com.relay.db.entity.location.Crossing;
import com.relay.db.entity.location.Location;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.location.TrackPoint;
import com.relay.db.repository.LocationRepository;
import com.relay.unit.GenericUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LocationRepositoryTest extends GenericUnitTest {

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        locationRepository.deleteAll();
    }

    @Test
    void createStation() {
        Station station = new Station();
        station.setName("Московская");

        Location saved = locationRepository.save(station);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Московская");
        assertThat(saved).isInstanceOf(Station.class);
    }

    @Test
    void createCrossing() {
        Crossing crossing = new Crossing();
        crossing.setName("Переезд 12 км");

        Location saved = locationRepository.save(crossing);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Переезд 12 км");
        assertThat(saved).isInstanceOf(Crossing.class);
    }

    @Test
    void createTrackPoint() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setName("Пикет 48 км");

        Location saved = locationRepository.save(trackPoint);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Пикет 48 км");
        assertThat(saved).isInstanceOf(TrackPoint.class);
    }

    @Test
    void findById() {
        Station station = new Station();
        station.setName("Петровская");
        Location saved = locationRepository.save(station);

        Optional<Location> found = locationRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Петровская");
        assertThat(found.get()).isInstanceOf(Station.class);
    }

    @Test
    void findByIdNotFound() {
        Optional<Location> found = locationRepository.findById(999L);

        assertThat(found).isEmpty();
    }

    @Test
    void findAll() {
        Station station = new Station();
        station.setName("Station");
        locationRepository.save(station);

        Crossing crossing = new Crossing();
        crossing.setName("Crossing");
        locationRepository.save(crossing);

        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setName("TrackPoint");
        locationRepository.save(trackPoint);

        List<Location> all = locationRepository.findAll();

        assertThat(all).hasSize(3);
    }

    @Test
    void updateLocation() {
        Station station = new Station();
        station.setName("Old Name");
        Location saved = locationRepository.save(station);

        saved.setName("New Name");
        Location updated = locationRepository.save(saved);

        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getName()).isEqualTo("New Name");
    }

    @Test
    void deleteById() {
        Station station = new Station();
        station.setName("To Delete");
        Location saved = locationRepository.save(station);

        locationRepository.deleteById(saved.getId());

        Optional<Location> found = locationRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }

    @Test
    void deleteAll() {
        Station station = new Station();
        station.setName("Station");
        locationRepository.save(station);

        Crossing crossing = new Crossing();
        crossing.setName("Crossing");
        locationRepository.save(crossing);

        locationRepository.deleteAll();

        assertThat(locationRepository.findAll()).isEmpty();
    }
}