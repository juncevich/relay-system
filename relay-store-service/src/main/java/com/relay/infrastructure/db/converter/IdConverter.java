package com.relay.infrastructure.db.converter;

import com.relay.infrastructure.id.SnowflakeId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
