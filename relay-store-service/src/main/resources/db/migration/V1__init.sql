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


-- Default data: участок Свердловской ж/д Екатеринбург — Алапаевск

-- Locations: 6 станций, 4 остановочных пункта, 3 переезда
INSERT INTO location (id, name)
VALUES (1, 'Екатеринбург-Пасс.');
INSERT INTO location (id, name)
VALUES (2, 'Первомайская');
INSERT INTO location (id, name)
VALUES (3, 'Монетная');
INSERT INTO location (id, name)
VALUES (4, 'Реж');
INSERT INTO location (id, name)
VALUES (5, 'Егоршино');
INSERT INTO location (id, name)
VALUES (6, 'Алапаевск');
INSERT INTO location (id, name)
VALUES (7, 'Шарташ');
INSERT INTO location (id, name)
VALUES (8, 'Березит');
INSERT INTO location (id, name)
VALUES (9, 'Адуй');
INSERT INTO location (id, name)
VALUES (10, 'Костоусово');
INSERT INTO location (id, name)
VALUES (11, 'Переезд 39 км');
INSERT INTO location (id, name)
VALUES (12, 'Переезд 85 км');
INSERT INTO location (id, name)
VALUES (13, 'Переезд 125 км');

INSERT INTO station (id)
VALUES (1);
INSERT INTO station (id)
VALUES (2);
INSERT INTO station (id)
VALUES (3);
INSERT INTO station (id)
VALUES (4);
INSERT INTO station (id)
VALUES (5);
INSERT INTO station (id)
VALUES (6);
INSERT INTO track_point (id)
VALUES (7);
INSERT INTO track_point (id)
VALUES (8);
INSERT INTO track_point (id)
VALUES (9);
INSERT INTO track_point (id)
VALUES (10);
INSERT INTO crossing (id)
VALUES (11);
INSERT INTO crossing (id)
VALUES (12);
INSERT INTO crossing (id)
VALUES (13);

ALTER SEQUENCE location_seq RESTART WITH 51;

-- Storages: 3 склада, 5 релейных шкафов, 3 стойки
INSERT INTO storage (id, name, location_id)
VALUES (101, 'Склад ШЧ Екатеринбург', 1);
INSERT INTO storage (id, name, location_id)
VALUES (102, 'Склад ШЧ Реж', 4);
INSERT INTO storage (id, name, location_id)
VALUES (103, 'Склад ШЧ Алапаевск', 6);
INSERT INTO storage (id, name, location_id)
VALUES (104, 'Релейный шкаф ст. Первомайская', 2);
INSERT INTO storage (id, name, location_id)
VALUES (105, 'Релейный шкаф ст. Монетная', 3);
INSERT INTO storage (id, name, location_id)
VALUES (106, 'Релейный шкаф ст. Егоршино', 5);
INSERT INTO storage (id, name, location_id)
VALUES (107, 'Релейный шкаф переезда 39 км', 11);
INSERT INTO storage (id, name, location_id)
VALUES (108, 'Релейный шкаф переезда 85 км', 12);
INSERT INTO storage (id, name, location_id)
VALUES (109, 'Стойка о.п. Шарташ', 7);
INSERT INTO storage (id, name, location_id)
VALUES (110, 'Стойка о.п. Березит', 8);
INSERT INTO storage (id, name, location_id)
VALUES (111, 'Стойка о.п. Адуй', 9);

INSERT INTO warehouse (id)
VALUES (101);
INSERT INTO warehouse (id)
VALUES (102);
INSERT INTO warehouse (id)
VALUES (103);
INSERT INTO relay_cabinet (id)
VALUES (104);
INSERT INTO relay_cabinet (id)
VALUES (105);
INSERT INTO relay_cabinet (id)
VALUES (106);
INSERT INTO relay_cabinet (id)
VALUES (107);
INSERT INTO relay_cabinet (id)
VALUES (108);
INSERT INTO stand (id)
VALUES (109);
INSERT INTO stand (id)
VALUES (110);
INSERT INTO stand (id)
VALUES (111);

-- Shelves
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (201, 1, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (202, 2, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (203, 3, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (204, 4, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (205, 5, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (206, 6, 10, 101);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (207, 1, 10, 102);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (208, 2, 10, 102);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (209, 3, 10, 102);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (210, 4, 10, 102);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (211, 1, 10, 103);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (212, 2, 10, 103);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (213, 3, 10, 103);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (214, 4, 10, 103);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (215, 1, 8, 104);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (216, 2, 8, 104);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (217, 1, 8, 105);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (218, 2, 8, 105);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (219, 1, 8, 106);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (220, 2, 8, 106);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (221, 1, 8, 107);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (222, 2, 8, 107);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (223, 1, 8, 108);
INSERT INTO shelf (id, number, capacity, storage_id)
VALUES (224, 2, 8, 108);

-- Relays
-- Склад ШЧ Екатеринбург (60 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (301, 1, 'НМШ-001', 'NMSH_400', 1, 101, 201, '2022-03-04 09:07:00+05', '2024-03-03 09:11:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (302, 1, 'НМШ-002', 'NMSH_400', 2, 101, 201, '2022-03-07 10:14:00+05', '2024-03-05 10:22:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (303, 1, 'РЭЛ-003', 'REL1_1600', 3, 101, 201, '2022-03-10 11:21:00+05', '2024-03-07 11:33:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (304, 1, 'РЭЛ-004', 'REL1_1600', 4, 101, 201, '2022-03-13 12:28:00+05', '2024-03-09 12:44:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (305, 1, 'РЭЛм-005', 'REL1M_600', 5, 101, 201, '2022-03-16 13:35:00+05', '2024-03-11 13:55:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (306, 1, 'НМШ-006', 'NMSH_400', 6, 101, 201, '2022-03-19 14:42:00+05', '2024-03-13 14:06:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (307, 1, 'НМШ-007', 'NMSH_400', 7, 101, 201, '2022-03-22 15:49:00+05', '2024-03-15 15:17:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (308, 1, 'НМШ-008', 'NMSH_400', 8, 101, 201, '2022-03-25 08:56:00+05', '2024-03-17 16:28:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (309, 1, 'РЭЛ-009', 'REL1_1600', 9, 101, 201, '2022-03-28 09:03:00+05', '2024-03-19 17:39:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (310, 1, 'РЭЛ-010', 'REL1_1600', 10, 101, 201, '2022-03-31 10:10:00+05', '2024-03-21 08:50:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (311, 1, 'РЭЛм-011', 'REL1M_600', 1, 101, 202, '2022-04-03 11:17:00+05', '2024-03-23 09:01:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (312, 1, 'НМШ-012', 'NMSH_400', 2, 101, 202, '2022-04-06 12:24:00+05', '2024-03-25 10:12:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (313, 1, 'НМШ-013', 'NMSH_400', 3, 101, 202, '2022-04-09 13:31:00+05', '2024-03-27 11:23:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (314, 1, 'НМШ-014', 'NMSH_400', 4, 101, 202, '2022-04-12 14:38:00+05', '2024-03-29 12:34:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (315, 1, 'РЭЛ-015', 'REL1_1600', 5, 101, 202, '2022-04-15 15:45:00+05', '2024-03-31 13:45:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (316, 1, 'РЭЛ-016', 'REL1_1600', 6, 101, 202, '2022-04-18 08:52:00+05', '2024-04-02 14:56:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (317, 1, 'РЭЛм-017', 'REL1M_600', 7, 101, 202, '2022-04-21 09:59:00+05', '2024-04-04 15:07:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (318, 1, 'НМШ-018', 'NMSH_400', 8, 101, 202, '2022-04-24 10:06:00+05', '2024-04-06 16:18:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (319, 1, 'НМШ-019', 'NMSH_400', 9, 101, 202, '2022-04-27 11:13:00+05', '2024-04-08 17:29:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (320, 1, 'НМШ-020', 'NMSH_400', 10, 101, 202, '2022-04-30 12:20:00+05', '2024-04-10 08:40:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (321, 1, 'РЭЛ-021', 'REL1_1600', 1, 101, 203, '2022-05-03 13:27:00+05', '2024-04-12 09:51:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (322, 1, 'РЭЛ-022', 'REL1_1600', 2, 101, 203, '2022-05-06 14:34:00+05', '2024-04-14 10:02:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (323, 1, 'РЭЛм-023', 'REL1M_600', 3, 101, 203, '2022-05-09 15:41:00+05', '2024-04-16 11:13:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (324, 1, 'НМШ-024', 'NMSH_400', 4, 101, 203, '2022-05-12 08:48:00+05', '2024-04-18 12:24:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (325, 1, 'НМШ-025', 'NMSH_400', 5, 101, 203, '2022-05-15 09:55:00+05', '2024-04-20 13:35:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (326, 1, 'НМШ-026', 'NMSH_400', 6, 101, 203, '2022-05-18 10:02:00+05', '2024-04-22 14:46:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (327, 1, 'РЭЛ-027', 'REL1_1600', 7, 101, 203, '2022-05-21 11:09:00+05', '2024-04-24 15:57:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (328, 1, 'РЭЛ-028', 'REL1_1600', 8, 101, 203, '2022-05-24 12:16:00+05', '2024-04-26 16:08:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (329, 1, 'РЭЛм-029', 'REL1M_600', 9, 101, 203, '2022-05-27 13:23:00+05', '2024-04-28 17:19:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (330, 1, 'НМШ-030', 'NMSH_400', 10, 101, 203, '2022-05-30 14:30:00+05', '2024-04-30 08:30:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (331, 1, 'НМШ-031', 'NMSH_400', 1, 101, 204, '2022-06-02 15:37:00+05', '2024-05-02 09:41:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (332, 1, 'НМШ-032', 'NMSH_400', 2, 101, 204, '2022-06-05 08:44:00+05', '2024-05-04 10:52:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (333, 1, 'РЭЛ-033', 'REL1_1600', 3, 101, 204, '2022-06-08 09:51:00+05', '2024-05-06 11:03:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (334, 1, 'РЭЛ-034', 'REL1_1600', 4, 101, 204, '2022-06-11 10:58:00+05', '2024-05-08 12:14:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (335, 1, 'РЭЛм-035', 'REL1M_600', 5, 101, 204, '2022-06-14 11:05:00+05', '2024-05-10 13:25:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (336, 1, 'НМШ-036', 'NMSH_400', 6, 101, 204, '2022-06-17 12:12:00+05', '2024-05-12 14:36:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (337, 1, 'НМШ-037', 'NMSH_400', 7, 101, 204, '2022-06-20 13:19:00+05', '2024-05-14 15:47:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (338, 1, 'НМШ-038', 'NMSH_400', 8, 101, 204, '2022-06-23 14:26:00+05', '2024-05-16 16:58:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (339, 1, 'РЭЛ-039', 'REL1_1600', 9, 101, 204, '2022-06-26 15:33:00+05', '2024-05-18 17:09:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (340, 1, 'РЭЛ-040', 'REL1_1600', 10, 101, 204, '2022-06-29 08:40:00+05', '2024-05-20 08:20:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (341, 1, 'РЭЛм-041', 'REL1M_600', 1, 101, 205, '2022-07-02 09:47:00+05', '2024-05-22 09:31:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (342, 1, 'НМШ-042', 'NMSH_400', 2, 101, 205, '2022-07-05 10:54:00+05', '2024-05-24 10:42:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (343, 1, 'НМШ-043', 'NMSH_400', 3, 101, 205, '2022-07-08 11:01:00+05', '2024-05-26 11:53:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (344, 1, 'НМШ-044', 'NMSH_400', 4, 101, 205, '2022-07-11 12:08:00+05', '2024-05-28 12:04:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (345, 1, 'РЭЛ-045', 'REL1_1600', 5, 101, 205, '2022-07-14 13:15:00+05', '2024-05-30 13:15:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (346, 1, 'РЭЛ-046', 'REL1_1600', 6, 101, 205, '2022-07-17 14:22:00+05', '2024-06-01 14:26:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (347, 1, 'РЭЛм-047', 'REL1M_600', 7, 101, 205, '2022-07-20 15:29:00+05', '2024-06-03 15:37:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (348, 1, 'НМШ-048', 'NMSH_400', 8, 101, 205, '2022-07-23 08:36:00+05', '2024-06-05 16:48:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (349, 1, 'НМШ-049', 'NMSH_400', 9, 101, 205, '2022-07-26 09:43:00+05', '2024-06-07 17:59:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (350, 1, 'НМШ-050', 'NMSH_400', 10, 101, 205, '2022-07-29 10:50:00+05', '2024-06-09 08:10:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (351, 1, 'РЭЛ-051', 'REL1_1600', 1, 101, 206, '2022-08-01 11:57:00+05', '2024-06-11 09:21:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (352, 1, 'РЭЛ-052', 'REL1_1600', 2, 101, 206, '2022-08-04 12:04:00+05', '2024-06-13 10:32:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (353, 1, 'РЭЛм-053', 'REL1M_600', 3, 101, 206, '2022-08-07 13:11:00+05', '2024-06-15 11:43:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (354, 1, 'НМШ-054', 'NMSH_400', 4, 101, 206, '2022-08-10 14:18:00+05', '2024-06-17 12:54:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (355, 1, 'НМШ-055', 'NMSH_400', 5, 101, 206, '2022-08-13 15:25:00+05', '2024-06-19 13:05:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (356, 1, 'НМШ-056', 'NMSH_400', 6, 101, 206, '2022-08-16 08:32:00+05', '2024-06-21 14:16:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (357, 1, 'РЭЛ-057', 'REL1_1600', 7, 101, 206, '2022-08-19 09:39:00+05', '2024-06-23 15:27:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (358, 1, 'РЭЛ-058', 'REL1_1600', 8, 101, 206, '2022-08-22 10:46:00+05', '2024-06-25 16:38:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (359, 1, 'РЭЛм-059', 'REL1M_600', 9, 101, 206, '2022-08-25 11:53:00+05', '2024-06-27 17:49:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (360, 1, 'НМШ-060', 'NMSH_400', 10, 101, 206, '2022-08-28 12:00:00+05', '2024-06-29 08:00:00+05');
-- Склад ШЧ Реж (40 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (361, 1, 'НМШ-061', 'NMSH_400', 1, 102, 207, '2022-08-31 13:07:00+05', '2024-07-01 09:11:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (362, 1, 'НМШ-062', 'NMSH_400', 2, 102, 207, '2022-09-03 14:14:00+05', '2024-07-03 10:22:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (363, 1, 'РЭЛ-063', 'REL1_1600', 3, 102, 207, '2022-09-06 15:21:00+05', '2024-07-05 11:33:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (364, 1, 'РЭЛ-064', 'REL1_1600', 4, 102, 207, '2022-09-09 08:28:00+05', '2024-07-07 12:44:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (365, 1, 'РЭЛм-065', 'REL1M_600', 5, 102, 207, '2022-09-12 09:35:00+05', '2024-07-09 13:55:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (366, 1, 'НМШ-066', 'NMSH_400', 6, 102, 207, '2022-09-15 10:42:00+05', '2024-07-11 14:06:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (367, 1, 'НМШ-067', 'NMSH_400', 7, 102, 207, '2022-09-18 11:49:00+05', '2024-07-13 15:17:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (368, 1, 'НМШ-068', 'NMSH_400', 8, 102, 207, '2022-09-21 12:56:00+05', '2024-07-15 16:28:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (369, 1, 'РЭЛ-069', 'REL1_1600', 9, 102, 207, '2022-09-24 13:03:00+05', '2024-07-17 17:39:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (370, 1, 'РЭЛ-070', 'REL1_1600', 10, 102, 207, '2022-09-27 14:10:00+05', '2024-07-19 08:50:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (371, 1, 'РЭЛм-071', 'REL1M_600', 1, 102, 208, '2022-09-30 15:17:00+05', '2024-07-21 09:01:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (372, 1, 'НМШ-072', 'NMSH_400', 2, 102, 208, '2022-10-03 08:24:00+05', '2024-07-23 10:12:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (373, 1, 'НМШ-073', 'NMSH_400', 3, 102, 208, '2022-10-06 09:31:00+05', '2024-07-25 11:23:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (374, 1, 'НМШ-074', 'NMSH_400', 4, 102, 208, '2022-10-09 10:38:00+05', '2024-07-27 12:34:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (375, 1, 'РЭЛ-075', 'REL1_1600', 5, 102, 208, '2022-10-12 11:45:00+05', '2024-07-29 13:45:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (376, 1, 'РЭЛ-076', 'REL1_1600', 6, 102, 208, '2022-10-15 12:52:00+05', '2024-07-31 14:56:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (377, 1, 'РЭЛм-077', 'REL1M_600', 7, 102, 208, '2022-10-18 13:59:00+05', '2024-08-02 15:07:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (378, 1, 'НМШ-078', 'NMSH_400', 8, 102, 208, '2022-10-21 14:06:00+05', '2024-08-04 16:18:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (379, 1, 'НМШ-079', 'NMSH_400', 9, 102, 208, '2022-10-24 15:13:00+05', '2024-08-06 17:29:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (380, 1, 'НМШ-080', 'NMSH_400', 10, 102, 208, '2022-10-27 08:20:00+05', '2024-08-08 08:40:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (381, 1, 'РЭЛ-081', 'REL1_1600', 1, 102, 209, '2022-10-30 09:27:00+05', '2024-08-10 09:51:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (382, 1, 'РЭЛ-082', 'REL1_1600', 2, 102, 209, '2022-11-02 10:34:00+05', '2024-08-12 10:02:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (383, 1, 'РЭЛм-083', 'REL1M_600', 3, 102, 209, '2022-11-05 11:41:00+05', '2024-08-14 11:13:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (384, 1, 'НМШ-084', 'NMSH_400', 4, 102, 209, '2022-11-08 12:48:00+05', '2024-08-16 12:24:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (385, 1, 'НМШ-085', 'NMSH_400', 5, 102, 209, '2022-11-11 13:55:00+05', '2024-08-18 13:35:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (386, 1, 'НМШ-086', 'NMSH_400', 6, 102, 209, '2022-11-14 14:02:00+05', '2024-08-20 14:46:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (387, 1, 'РЭЛ-087', 'REL1_1600', 7, 102, 209, '2022-11-17 15:09:00+05', '2024-08-22 15:57:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (388, 1, 'РЭЛ-088', 'REL1_1600', 8, 102, 209, '2022-11-20 08:16:00+05', '2024-08-24 16:08:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (389, 1, 'РЭЛм-089', 'REL1M_600', 9, 102, 209, '2022-11-23 09:23:00+05', '2024-08-26 17:19:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (390, 1, 'НМШ-090', 'NMSH_400', 10, 102, 209, '2022-11-26 10:30:00+05', '2024-08-28 08:30:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (391, 1, 'НМШ-091', 'NMSH_400', 1, 102, 210, '2022-11-29 11:37:00+05', '2024-08-30 09:41:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (392, 1, 'НМШ-092', 'NMSH_400', 2, 102, 210, '2022-12-02 12:44:00+05', '2024-09-01 10:52:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (393, 1, 'РЭЛ-093', 'REL1_1600', 3, 102, 210, '2022-12-05 13:51:00+05', '2024-09-03 11:03:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (394, 1, 'РЭЛ-094', 'REL1_1600', 4, 102, 210, '2022-12-08 14:58:00+05', '2024-09-05 12:14:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (395, 1, 'РЭЛм-095', 'REL1M_600', 5, 102, 210, '2022-12-11 15:05:00+05', '2024-09-07 13:25:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (396, 1, 'НМШ-096', 'NMSH_400', 6, 102, 210, '2022-12-14 08:12:00+05', '2024-09-09 14:36:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (397, 1, 'НМШ-097', 'NMSH_400', 7, 102, 210, '2022-12-17 09:19:00+05', '2024-09-11 15:47:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (398, 1, 'НМШ-098', 'NMSH_400', 8, 102, 210, '2022-12-20 10:26:00+05', '2024-09-13 16:58:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (399, 1, 'РЭЛ-099', 'REL1_1600', 9, 102, 210, '2022-12-23 11:33:00+05', '2024-09-15 17:09:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (400, 1, 'РЭЛ-100', 'REL1_1600', 10, 102, 210, '2022-12-26 12:40:00+05', '2024-09-17 08:20:00+05');
-- Склад ШЧ Алапаевск (35 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (401, 1, 'РЭЛм-101', 'REL1M_600', 1, 103, 211, '2022-12-29 13:47:00+05', '2024-09-19 09:31:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (402, 1, 'НМШ-102', 'NMSH_400', 2, 103, 211, '2023-01-01 14:54:00+05', '2024-09-21 10:42:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (403, 1, 'НМШ-103', 'NMSH_400', 3, 103, 211, '2023-01-04 15:01:00+05', '2024-09-23 11:53:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (404, 1, 'НМШ-104', 'NMSH_400', 4, 103, 211, '2023-01-07 08:08:00+05', '2024-09-25 12:04:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (405, 1, 'РЭЛ-105', 'REL1_1600', 5, 103, 211, '2023-01-10 09:15:00+05', '2024-09-27 13:15:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (406, 1, 'РЭЛ-106', 'REL1_1600', 6, 103, 211, '2023-01-13 10:22:00+05', '2024-09-29 14:26:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (407, 1, 'РЭЛм-107', 'REL1M_600', 7, 103, 211, '2023-01-16 11:29:00+05', '2024-10-01 15:37:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (408, 1, 'НМШ-108', 'NMSH_400', 8, 103, 211, '2023-01-19 12:36:00+05', '2024-10-03 16:48:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (409, 1, 'НМШ-109', 'NMSH_400', 9, 103, 211, '2023-01-22 13:43:00+05', '2024-10-05 17:59:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (410, 1, 'НМШ-110', 'NMSH_400', 10, 103, 211, '2023-01-25 14:50:00+05', '2024-10-07 08:10:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (411, 1, 'РЭЛ-111', 'REL1_1600', 1, 103, 212, '2023-01-28 15:57:00+05', '2024-10-09 09:21:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (412, 1, 'РЭЛ-112', 'REL1_1600', 2, 103, 212, '2023-01-31 08:04:00+05', '2024-10-11 10:32:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (413, 1, 'РЭЛм-113', 'REL1M_600', 3, 103, 212, '2023-02-03 09:11:00+05', '2024-10-13 11:43:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (414, 1, 'НМШ-114', 'NMSH_400', 4, 103, 212, '2023-02-06 10:18:00+05', '2024-10-15 12:54:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (415, 1, 'НМШ-115', 'NMSH_400', 5, 103, 212, '2023-02-09 11:25:00+05', '2024-10-17 13:05:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (416, 1, 'НМШ-116', 'NMSH_400', 6, 103, 212, '2023-02-12 12:32:00+05', '2024-10-19 14:16:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (417, 1, 'РЭЛ-117', 'REL1_1600', 7, 103, 212, '2023-02-15 13:39:00+05', '2024-10-21 15:27:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (418, 1, 'РЭЛ-118', 'REL1_1600', 8, 103, 212, '2023-02-18 14:46:00+05', '2024-10-23 16:38:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (419, 1, 'РЭЛм-119', 'REL1M_600', 9, 103, 212, '2023-02-21 15:53:00+05', '2024-10-25 17:49:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (420, 1, 'НМШ-120', 'NMSH_400', 10, 103, 212, '2023-02-24 08:00:00+05', '2024-10-27 08:00:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (421, 1, 'НМШ-121', 'NMSH_400', 1, 103, 213, '2023-02-27 09:07:00+05', '2024-10-29 09:11:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (422, 1, 'НМШ-122', 'NMSH_400', 2, 103, 213, '2023-03-02 10:14:00+05', '2024-10-31 10:22:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (423, 1, 'РЭЛ-123', 'REL1_1600', 3, 103, 213, '2023-03-05 11:21:00+05', '2024-11-02 11:33:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (424, 1, 'РЭЛ-124', 'REL1_1600', 4, 103, 213, '2023-03-08 12:28:00+05', '2024-11-04 12:44:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (425, 1, 'РЭЛм-125', 'REL1M_600', 5, 103, 213, '2023-03-11 13:35:00+05', '2024-11-06 13:55:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (426, 1, 'НМШ-126', 'NMSH_400', 6, 103, 213, '2023-03-14 14:42:00+05', '2024-11-08 14:06:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (427, 1, 'НМШ-127', 'NMSH_400', 7, 103, 213, '2023-03-17 15:49:00+05', '2024-11-10 15:17:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (428, 1, 'НМШ-128', 'NMSH_400', 8, 103, 213, '2023-03-20 08:56:00+05', '2024-11-12 16:28:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (429, 1, 'РЭЛ-129', 'REL1_1600', 9, 103, 213, '2023-03-23 09:03:00+05', '2024-11-14 17:39:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (430, 1, 'РЭЛ-130', 'REL1_1600', 10, 103, 213, '2023-03-26 10:10:00+05', '2024-11-16 08:50:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (431, 1, 'РЭЛм-131', 'REL1M_600', 1, 103, 214, '2023-03-29 11:17:00+05', '2024-11-18 09:01:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (432, 1, 'НМШ-132', 'NMSH_400', 2, 103, 214, '2023-04-01 12:24:00+05', '2024-11-20 10:12:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (433, 1, 'НМШ-133', 'NMSH_400', 3, 103, 214, '2023-04-04 13:31:00+05', '2024-11-22 11:23:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (434, 1, 'НМШ-134', 'NMSH_400', 4, 103, 214, '2023-04-07 14:38:00+05', '2024-11-24 12:34:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (435, 1, 'РЭЛ-135', 'REL1_1600', 5, 103, 214, '2023-04-10 15:45:00+05', '2024-11-26 13:45:00+05');
-- Релейный шкаф ст. Первомайская (16 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (436, 1, 'РЭЛ-136', 'REL1_1600', 1, 104, 215, '2023-04-13 08:52:00+05', '2024-11-28 14:56:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (437, 1, 'РЭЛм-137', 'REL1M_600', 2, 104, 215, '2023-04-16 09:59:00+05', '2024-11-30 15:07:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (438, 1, 'НМШ-138', 'NMSH_400', 3, 104, 215, '2023-04-19 10:06:00+05', '2024-12-02 16:18:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (439, 1, 'НМШ-139', 'NMSH_400', 4, 104, 215, '2023-04-22 11:13:00+05', '2024-12-04 17:29:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (440, 1, 'НМШ-140', 'NMSH_400', 5, 104, 215, '2023-04-25 12:20:00+05', '2024-12-06 08:40:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (441, 1, 'РЭЛ-141', 'REL1_1600', 6, 104, 215, '2023-04-28 13:27:00+05', '2024-12-08 09:51:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (442, 1, 'РЭЛ-142', 'REL1_1600', 7, 104, 215, '2023-05-01 14:34:00+05', '2024-12-10 10:02:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (443, 1, 'РЭЛм-143', 'REL1M_600', 8, 104, 215, '2023-05-04 15:41:00+05', '2024-12-12 11:13:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (444, 1, 'НМШ-144', 'NMSH_400', 1, 104, 216, '2023-05-07 08:48:00+05', '2024-12-14 12:24:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (445, 1, 'НМШ-145', 'NMSH_400', 2, 104, 216, '2023-05-10 09:55:00+05', '2024-12-16 13:35:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (446, 1, 'НМШ-146', 'NMSH_400', 3, 104, 216, '2023-05-13 10:02:00+05', '2024-12-18 14:46:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (447, 1, 'РЭЛ-147', 'REL1_1600', 4, 104, 216, '2023-05-16 11:09:00+05', '2024-12-20 15:57:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (448, 1, 'РЭЛ-148', 'REL1_1600', 5, 104, 216, '2023-05-19 12:16:00+05', '2024-12-22 16:08:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (449, 1, 'РЭЛм-149', 'REL1M_600', 6, 104, 216, '2023-05-22 13:23:00+05', '2024-12-24 17:19:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (450, 1, 'НМШ-150', 'NMSH_400', 7, 104, 216, '2023-05-25 14:30:00+05', '2024-12-26 08:30:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (451, 1, 'НМШ-151', 'NMSH_400', 8, 104, 216, '2023-05-28 15:37:00+05', '2024-12-28 09:41:00+05');
-- Релейный шкаф ст. Монетная (16 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (452, 1, 'НМШ-152', 'NMSH_400', 1, 105, 217, '2023-05-31 08:44:00+05', '2024-12-30 10:52:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (453, 1, 'РЭЛ-153', 'REL1_1600', 2, 105, 217, '2023-06-03 09:51:00+05', '2025-01-01 11:03:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (454, 1, 'РЭЛ-154', 'REL1_1600', 3, 105, 217, '2023-06-06 10:58:00+05', '2025-01-03 12:14:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (455, 1, 'РЭЛм-155', 'REL1M_600', 4, 105, 217, '2023-06-09 11:05:00+05', '2025-01-05 13:25:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (456, 1, 'НМШ-156', 'NMSH_400', 5, 105, 217, '2023-06-12 12:12:00+05', '2025-01-07 14:36:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (457, 1, 'НМШ-157', 'NMSH_400', 6, 105, 217, '2023-06-15 13:19:00+05', '2025-01-09 15:47:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (458, 1, 'НМШ-158', 'NMSH_400', 7, 105, 217, '2023-06-18 14:26:00+05', '2025-01-11 16:58:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (459, 1, 'РЭЛ-159', 'REL1_1600', 8, 105, 217, '2023-06-21 15:33:00+05', '2025-01-13 17:09:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (460, 1, 'РЭЛ-160', 'REL1_1600', 1, 105, 218, '2023-06-24 08:40:00+05', '2025-01-15 08:20:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (461, 1, 'РЭЛм-161', 'REL1M_600', 2, 105, 218, '2023-06-27 09:47:00+05', '2025-01-17 09:31:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (462, 1, 'НМШ-162', 'NMSH_400', 3, 105, 218, '2023-06-30 10:54:00+05', '2025-01-19 10:42:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (463, 1, 'НМШ-163', 'NMSH_400', 4, 105, 218, '2023-07-03 11:01:00+05', '2025-01-21 11:53:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (464, 1, 'НМШ-164', 'NMSH_400', 5, 105, 218, '2023-07-06 12:08:00+05', '2025-01-23 12:04:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (465, 1, 'РЭЛ-165', 'REL1_1600', 6, 105, 218, '2023-07-09 13:15:00+05', '2025-01-25 13:15:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (466, 1, 'РЭЛ-166', 'REL1_1600', 7, 105, 218, '2023-07-12 14:22:00+05', '2025-01-27 14:26:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (467, 1, 'РЭЛм-167', 'REL1M_600', 8, 105, 218, '2023-07-15 15:29:00+05', '2025-01-29 15:37:00+05');
-- Релейный шкаф ст. Егоршино (16 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (468, 1, 'НМШ-168', 'NMSH_400', 1, 106, 219, '2023-07-18 08:36:00+05', '2025-01-31 16:48:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (469, 1, 'НМШ-169', 'NMSH_400', 2, 106, 219, '2023-07-21 09:43:00+05', '2025-02-02 17:59:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (470, 1, 'НМШ-170', 'NMSH_400', 3, 106, 219, '2023-07-24 10:50:00+05', '2025-02-04 08:10:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (471, 1, 'РЭЛ-171', 'REL1_1600', 4, 106, 219, '2023-07-27 11:57:00+05', '2025-02-06 09:21:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (472, 1, 'РЭЛ-172', 'REL1_1600', 5, 106, 219, '2023-07-30 12:04:00+05', '2025-02-08 10:32:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (473, 1, 'РЭЛм-173', 'REL1M_600', 6, 106, 219, '2023-08-02 13:11:00+05', '2025-02-10 11:43:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (474, 1, 'НМШ-174', 'NMSH_400', 7, 106, 219, '2023-08-05 14:18:00+05', '2025-02-12 12:54:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (475, 1, 'НМШ-175', 'NMSH_400', 8, 106, 219, '2023-08-08 15:25:00+05', '2025-02-14 13:05:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (476, 1, 'НМШ-176', 'NMSH_400', 1, 106, 220, '2023-08-11 08:32:00+05', '2025-02-16 14:16:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (477, 1, 'РЭЛ-177', 'REL1_1600', 2, 106, 220, '2023-08-14 09:39:00+05', '2025-02-18 15:27:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (478, 1, 'РЭЛ-178', 'REL1_1600', 3, 106, 220, '2023-08-17 10:46:00+05', '2025-02-20 16:38:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (479, 1, 'РЭЛм-179', 'REL1M_600', 4, 106, 220, '2023-08-20 11:53:00+05', '2025-02-22 17:49:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (480, 1, 'НМШ-180', 'NMSH_400', 5, 106, 220, '2023-08-23 12:00:00+05', '2025-02-24 08:00:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (481, 1, 'НМШ-181', 'NMSH_400', 6, 106, 220, '2023-08-26 13:07:00+05', '2025-02-26 09:11:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (482, 1, 'НМШ-182', 'NMSH_400', 7, 106, 220, '2023-08-29 14:14:00+05', '2025-02-28 10:22:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (483, 1, 'РЭЛ-183', 'REL1_1600', 8, 106, 220, '2023-09-01 15:21:00+05', '2025-03-02 11:33:00+05');
-- Релейный шкаф переезда 39 км (16 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (484, 1, 'РЭЛ-184', 'REL1_1600', 1, 107, 221, '2023-09-04 08:28:00+05', '2025-03-04 12:44:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (485, 1, 'РЭЛм-185', 'REL1M_600', 2, 107, 221, '2023-09-07 09:35:00+05', '2025-03-06 13:55:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (486, 1, 'НМШ-186', 'NMSH_400', 3, 107, 221, '2023-09-10 10:42:00+05', '2025-03-08 14:06:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (487, 1, 'НМШ-187', 'NMSH_400', 4, 107, 221, '2023-09-13 11:49:00+05', '2025-03-10 15:17:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (488, 1, 'НМШ-188', 'NMSH_400', 5, 107, 221, '2023-09-16 12:56:00+05', '2025-03-12 16:28:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (489, 1, 'РЭЛ-189', 'REL1_1600', 6, 107, 221, '2023-09-19 13:03:00+05', '2025-03-14 17:39:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (490, 1, 'РЭЛ-190', 'REL1_1600', 7, 107, 221, '2023-09-22 14:10:00+05', '2025-03-16 08:50:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (491, 1, 'РЭЛм-191', 'REL1M_600', 8, 107, 221, '2023-09-25 15:17:00+05', '2025-03-18 09:01:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (492, 1, 'НМШ-192', 'NMSH_400', 1, 107, 222, '2023-09-28 08:24:00+05', '2025-03-20 10:12:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (493, 1, 'НМШ-193', 'NMSH_400', 2, 107, 222, '2023-10-01 09:31:00+05', '2025-03-22 11:23:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (494, 1, 'НМШ-194', 'NMSH_400', 3, 107, 222, '2023-10-04 10:38:00+05', '2025-03-24 12:34:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (495, 1, 'РЭЛ-195', 'REL1_1600', 4, 107, 222, '2023-10-07 11:45:00+05', '2025-03-26 13:45:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (496, 1, 'РЭЛ-196', 'REL1_1600', 5, 107, 222, '2023-10-10 12:52:00+05', '2025-03-28 14:56:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (497, 1, 'РЭЛм-197', 'REL1M_600', 6, 107, 222, '2023-10-13 13:59:00+05', '2025-03-30 15:07:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (498, 1, 'НМШ-198', 'NMSH_400', 7, 107, 222, '2023-10-16 14:06:00+05', '2025-04-01 16:18:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (499, 1, 'НМШ-199', 'NMSH_400', 8, 107, 222, '2023-10-19 15:13:00+05', '2025-04-03 17:29:00+05');
-- Релейный шкаф переезда 85 км (16 реле)
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (500, 1, 'НМШ-200', 'NMSH_400', 1, 108, 223, '2023-10-22 08:20:00+05', '2025-04-05 08:40:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (501, 1, 'РЭЛ-201', 'REL1_1600', 2, 108, 223, '2023-10-25 09:27:00+05', '2025-04-07 09:51:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (502, 1, 'РЭЛ-202', 'REL1_1600', 3, 108, 223, '2023-10-28 10:34:00+05', '2025-04-09 10:02:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (503, 1, 'РЭЛм-203', 'REL1M_600', 4, 108, 223, '2023-10-31 11:41:00+05', '2025-04-11 11:13:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (504, 1, 'НМШ-204', 'NMSH_400', 5, 108, 223, '2023-11-03 12:48:00+05', '2025-04-13 12:24:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (505, 1, 'НМШ-205', 'NMSH_400', 6, 108, 223, '2023-11-06 13:55:00+05', '2025-04-15 13:35:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (506, 1, 'НМШ-206', 'NMSH_400', 7, 108, 223, '2023-11-09 14:02:00+05', '2025-04-17 14:46:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (507, 1, 'РЭЛ-207', 'REL1_1600', 8, 108, 223, '2023-11-12 15:09:00+05', '2025-04-19 15:57:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (508, 1, 'РЭЛ-208', 'REL1_1600', 1, 108, 224, '2023-11-15 08:16:00+05', '2025-04-21 16:08:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (509, 1, 'РЭЛм-209', 'REL1M_600', 2, 108, 224, '2023-11-18 09:23:00+05', '2025-04-23 17:19:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (510, 1, 'НМШ-210', 'NMSH_400', 3, 108, 224, '2023-11-21 10:30:00+05', '2025-04-25 08:30:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (511, 1, 'НМШ-211', 'NMSH_400', 4, 108, 224, '2023-11-24 11:37:00+05', '2025-04-27 09:41:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (512, 1, 'НМШ-212', 'NMSH_400', 5, 108, 224, '2023-11-27 12:44:00+05', '2025-04-29 10:52:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (513, 1, 'РЭЛ-213', 'REL1_1600', 6, 108, 224, '2023-11-30 13:51:00+05', '2025-05-01 11:03:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (514, 1, 'РЭЛ-214', 'REL1_1600', 7, 108, 224, '2023-12-03 14:58:00+05', '2025-05-03 12:14:00+05');
INSERT INTO relay (id, version, serial_number, relay_type, place_number, storage_id, shelf_id, created_at,
                   last_check_date)
VALUES (515, 1, 'РЭЛм-215', 'REL1M_600', 8, 108, 224, '2023-12-06 15:05:00+05', '2025-05-05 13:25:00+05');

-- Итого: 215 реле

