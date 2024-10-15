CREATE SEQUENCE IF NOT EXISTS seq_relay INCREMENT BY 5;

create table relay
(
    id              int not null,
    creation_date   TIMESTAMP WITH TIME ZONE,
    last_check_date timestamp,
    relay_type      varchar(255),
    serial_number   varchar(255),
    update_date     TIMESTAMP WITH TIME ZONE,
    version         int,
    container_id    int,
    primary key (id)
);

-- CREATE USER IF NOT EXISTS sample_user;
-- ALTER ROLE sample_user WITH PASSWORD 'example';
GRANT SELECT ON TABLE relay TO sample_user;

INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (1, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (2, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (3, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (4, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (5, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (6, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (7, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (8, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (9, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (10, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (11, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (12, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (13, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (14, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (15, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (16, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (17, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (18, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (19, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (21, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (22, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (23, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (24, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (25, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (26, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (27, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (28, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (29, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (31, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (32, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (33, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (34, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (35, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
INSERT INTO relay (id, creation_date, last_check_date, relay_type, serial_number, update_date, version, container_id)
VALUES (36, '2020-11-21 17:53:45.684000', '2020-11-21 18:53:53.000000', null, '1234567', null, 1, null);
