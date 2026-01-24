package com.relay.unit.db;

import com.relay.db.entity.location.Station;
import com.relay.db.entity.storage.Shelf;
import com.relay.db.entity.storage.Storage;
import com.relay.db.entity.storage.Warehouse;
import com.relay.db.repository.LocationRepository;
import com.relay.db.repository.ShelfRepository;
import com.relay.db.repository.WarehouseRepository;
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
class ShelfRepositoryTest extends GenericUnitTest {

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private LocationRepository locationRepository;

    private Storage defaultStorage;

    @BeforeEach
    void setUp() {
        shelfRepository.deleteAll();
        warehouseRepository.deleteAll();
        locationRepository.deleteAll();

        Station station = new Station();
        station.setName("Test Station");
        locationRepository.save(station);

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Test Warehouse");
        warehouse.setLocation(station);
        warehouseRepository.save(warehouse);

        defaultStorage = warehouse;
    }

    @Test
    void createShelf() {
        Shelf shelf = new Shelf();
        shelf.setNumber(1);
        shelf.setCapacity(10);
        shelf.setStorage(defaultStorage);

        Shelf saved = shelfRepository.save(shelf);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNumber()).isEqualTo(1);
        assertThat(saved.getCapacity()).isEqualTo(10);
        assertThat(saved.getStorage().getId()).isEqualTo(defaultStorage.getId());
    }

    @Test
    void findById() {
        Shelf shelf = new Shelf();
        shelf.setNumber(2);
        shelf.setCapacity(8);
        shelf.setStorage(defaultStorage);
        Shelf saved = shelfRepository.save(shelf);

        Optional<Shelf> found = shelfRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getNumber()).isEqualTo(2);
        assertThat(found.get().getCapacity()).isEqualTo(8);
    }

    @Test
    void findByIdNotFound() {
        Optional<Shelf> found = shelfRepository.findById(999L);

        assertThat(found).isEmpty();
    }

    @Test
    void findAll() {
        Shelf shelf1 = new Shelf();
        shelf1.setNumber(1);
        shelf1.setCapacity(10);
        shelf1.setStorage(defaultStorage);
        shelfRepository.save(shelf1);

        Shelf shelf2 = new Shelf();
        shelf2.setNumber(2);
        shelf2.setCapacity(8);
        shelf2.setStorage(defaultStorage);
        shelfRepository.save(shelf2);

        List<Shelf> all = shelfRepository.findAll();

        assertThat(all).hasSize(2);
    }

    @Test
    void updateShelf() {
        Shelf shelf = new Shelf();
        shelf.setNumber(1);
        shelf.setCapacity(10);
        shelf.setStorage(defaultStorage);
        Shelf saved = shelfRepository.save(shelf);

        saved.setCapacity(12);
        Shelf updated = shelfRepository.save(saved);

        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getCapacity()).isEqualTo(12);
    }

    @Test
    void deleteById() {
        Shelf shelf = new Shelf();
        shelf.setNumber(1);
        shelf.setCapacity(10);
        shelf.setStorage(defaultStorage);
        Shelf saved = shelfRepository.save(shelf);

        shelfRepository.deleteById(saved.getId());

        Optional<Shelf> found = shelfRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }

    @Test
    void deleteAll() {
        Shelf shelf1 = new Shelf();
        shelf1.setNumber(1);
        shelf1.setCapacity(10);
        shelf1.setStorage(defaultStorage);
        shelfRepository.save(shelf1);

        Shelf shelf2 = new Shelf();
        shelf2.setNumber(2);
        shelf2.setCapacity(6);
        shelf2.setStorage(defaultStorage);
        shelfRepository.save(shelf2);

        shelfRepository.deleteAll();

        assertThat(shelfRepository.findAll()).isEmpty();
    }
}