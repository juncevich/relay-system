package ru.relay.infrastructure.db.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.relay.infrastructure.id.SnowflakeId;

@Converter
public class IdConverter implements
        AttributeConverter<SnowflakeId, Long> {
    @Override
    public Long convertToDatabaseColumn(SnowflakeId attribute) {
        return attribute.getRaw();
    }

    @Override
    public SnowflakeId convertToEntityAttribute(Long dbData) {
        return new SnowflakeId(dbData);
    }
}
