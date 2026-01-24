-- Location hierarchy (JOINED inheritance)
CREATE SEQUENCE IF NOT EXISTS location_seq INCREMENT BY 50;

CREATE TABLE location
(
    id   bigint NOT NULL DEFAULT nextval('location_seq'),
    name varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE station
(
    id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES location (id)
);

CREATE TABLE crossing
(
    id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES location (id)
);

CREATE TABLE track_point
(
    id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES location (id)
);

-- Storage hierarchy (JOINED inheritance)
CREATE TABLE storage
(
    id          bigint NOT NULL,
    name        varchar(255),
    location_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE warehouse
(
    id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES storage (id)
);

CREATE TABLE relay_cabinet
(
    id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES storage (id)
);

CREATE TABLE stand
(
    id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES storage (id)
);

-- Shelf
CREATE TABLE shelf
(
    id         bigint NOT NULL,
    number     int    NOT NULL,
    capacity   int    NOT NULL,
    storage_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (storage_id) REFERENCES storage (id)
);

-- Relay
CREATE TABLE relay
(
    id              bigint NOT NULL,
    version         bigint,
    serial_number   varchar(255),
    relay_type      varchar(255),
    place_number    int    NOT NULL,
    storage_id      bigint NOT NULL,
    shelf_id        bigint,
    created_at      TIMESTAMP WITH TIME ZONE,
    updated_at      TIMESTAMP WITH TIME ZONE,
    last_check_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id),
    FOREIGN KEY (storage_id) REFERENCES storage (id),
    FOREIGN KEY (shelf_id) REFERENCES shelf (id)
);


-- Default data

-- Locations (2 stations, 2 crossings, 1 track_point)
INSERT INTO location (id, name)
VALUES (1, 'Московская');
INSERT INTO location (id, name)
VALUES (2, 'Петровская');
INSERT INTO location (id, name)
VALUES (3, 'Переезд 12 км');
INSERT INTO location (id, name)
VALUES (4, 'Переезд 25 км');
INSERT INTO location (id, name)
VALUES (5, 'Пикет 48 км');

INSERT INTO station (id)
VALUES (1);
INSERT INTO station (id)
VALUES (2);
INSERT INTO crossing (id)
VALUES (3);
INSERT INTO crossing (id)
VALUES (4);
INSERT INTO track_point (id)
VALUES (5);

ALTER SEQUENCE location_seq RESTART WITH 51;

-- Storages (2 warehouses, 2 relay_cabinets, 1 stand)
INSERT INTO storage (id, name, location_id)
VALUES (101, 'Склад №1', 1);
INSERT INTO storage (id, name, location_id)
VALUES (102, 'Склад №2', 2);
INSERT INTO storage (id, name, location_id)
VALUES (103, 'Релейный шкаф №1', 3);
INSERT INTO storage (id, name, location_id)
VALUES (104, 'Релейный шкаф №2', 4);
INSERT INTO storage (id, name, location_id)
VALUES (105, 'Стойка №1', 5);

INSERT INTO warehouse (id)
VALUES (101);
INSERT INTO warehouse (id)
VALUES (102);
INSERT INTO relay_cabinet (id)
VALUES (103);
INSERT INTO relay_cabinet (id)
VALUES (104);
INSERT INTO stand (id)
VALUES (105);

-- Shelves
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (201, 1, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (202, 2, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (203, 1, 8, 102);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (204, 1, 6, 103);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (205, 1, 6, 104);

-- Relays
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (301, 1, 'НМШ-001', 'NMSH_400', 1, 101, 201, '2024-01-15 10:00:00+03', '2024-06-01 09:00:00+03');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (302, 1, 'НМШ-002', 'NMSH_400', 2, 101, 201, '2024-01-15 10:05:00+03', '2024-06-01 09:30:00+03');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (303, 1, 'РЭЛ-003', 'REL1_1600', 1, 102, 203, '2024-02-20 14:00:00+03', '2024-07-10 11:00:00+03');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (304, 1, 'РЭЛ-004', 'REL1_1600', 1, 103, 204, '2024-03-10 08:30:00+03', '2024-08-15 14:00:00+03');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (305, 1, 'РЭЛ-005', 'REL1M_600', 1, 104, 205, '2024-04-05 16:00:00+03', '2024-09-20 10:00:00+03');

