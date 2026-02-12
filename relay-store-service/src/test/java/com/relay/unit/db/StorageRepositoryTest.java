package com.relay.unit.db;

import com.relay.db.dao.*;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.storage.RelayCabinet;
import com.relay.db.entity.storage.Stand;
import com.relay.db.entity.storage.Storage;
import com.relay.db.entity.storage.Warehouse;
import com.relay.unit.GenericUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StorageRepositoryTest extends GenericUnitTest {

    @Autowired
    private StorageDao storageRepository;

    @Autowired
    private WarehouseDao warehouseRepository;

    @Autowired
    private RelayCabinetDao relayCabinetRepository;

    @Autowired
    private StandDao standRepository;

    @Autowired
    private LocationDao locationRepository;

    private Station defaultLocation;

    @BeforeEach
    void setUp() {
        storageRepository.deleteAll();
        locationRepository.deleteAll();

        defaultLocation = new Station();
        defaultLocation.setName("Test Station");
        locationRepository.save(defaultLocation);
    }

    @Test
    void createWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Склад №1");
        warehouse.setLocation(defaultLocation);

        Warehouse saved = warehouseRepository.save(warehouse);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Склад №1");
        assertThat(saved.getLocation().getId()).isEqualTo(defaultLocation.getId());
    }

    @Test
    void createRelayCabinet() {
        RelayCabinet cabinet = new RelayCabinet();
        cabinet.setName("Релейный шкаф №1");
        cabinet.setLocation(defaultLocation);

        RelayCabinet saved = relayCabinetRepository.save(cabinet);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Релейный шкаф №1");
    }

    @Test
    void createStand() {
        Stand stand = new Stand();
        stand.setName("Стойка №1");
        stand.setLocation(defaultLocation);

        Stand saved = standRepository.save(stand);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Стойка №1");
    }

    @Test
    void findAllStoragesPolymorphic() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse");
        warehouse.setLocation(defaultLocation);
        warehouseRepository.save(warehouse);

        Stand stand = new Stand();
        stand.setName("Stand");
        stand.setLocation(defaultLocation);
        standRepository.save(stand);

        RelayCabinet cabinet = new RelayCabinet();
        cabinet.setName("Cabinet");
        cabinet.setLocation(defaultLocation);
        relayCabinetRepository.save(cabinet);

        List<Storage> all = storageRepository.findAll();

        assertThat(all).hasSize(3);
        assertThat(all).anyMatch(s -> s instanceof Warehouse);
        assertThat(all).anyMatch(s -> s instanceof Stand);
        assertThat(all).anyMatch(s -> s instanceof RelayCabinet);
    }

    @Test
    void findById() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Склад");
        warehouse.setLocation(defaultLocation);
        Warehouse saved = warehouseRepository.save(warehouse);

        Optional<Storage> found = storageRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get()).isInstanceOf(Warehouse.class);
        assertThat(found.get().getName()).isEqualTo("Склад");
    }

    @Test
    void findByIdNotFound() {
        Optional<Storage> found = storageRepository.findById(999L);

        assertThat(found).isEmpty();
    }

    @Test
    void updateStorage() {
        Stand stand = new Stand();
        stand.setName("Old Name");
        stand.setLocation(defaultLocation);
        Stand saved = standRepository.save(stand);

        saved.setName("New Name");
        Stand updated = standRepository.save(saved);

        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getName()).isEqualTo("New Name");
    }

    @Test
    void deleteById() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("To Delete");
        warehouse.setLocation(defaultLocation);
        Warehouse saved = warehouseRepository.save(warehouse);

        warehouseRepository.deleteById(saved.getId());

        Optional<Warehouse> found = warehouseRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }

    @Test
    void deleteAll() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse");
        warehouse.setLocation(defaultLocation);
        warehouseRepository.save(warehouse);

        Stand stand = new Stand();
        stand.setName("Stand");
        stand.setLocation(defaultLocation);
        standRepository.save(stand);

        storageRepository.deleteAll();

        assertThat(storageRepository.findAll()).isEmpty();
    }
}