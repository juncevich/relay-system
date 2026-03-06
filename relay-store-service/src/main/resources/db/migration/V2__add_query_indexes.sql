CREATE UNIQUE INDEX IF NOT EXISTS ux_relay_serial_number
    ON relay (serial_number)
    WHERE serial_number IS NOT NULL;

CREATE INDEX IF NOT EXISTS ix_relay_created_at
    ON relay (created_at);

CREATE INDEX IF NOT EXISTS ix_relay_last_check_date
    ON relay (last_check_date);

CREATE INDEX IF NOT EXISTS ix_relay_storage_id
    ON relay (storage_id);

CREATE INDEX IF NOT EXISTS ix_relay_shelf_id
    ON relay (shelf_id);

CREATE INDEX IF NOT EXISTS ix_storage_location_id
    ON storage (location_id);

CREATE INDEX IF NOT EXISTS ix_shelf_storage_id
    ON shelf (storage_id);
