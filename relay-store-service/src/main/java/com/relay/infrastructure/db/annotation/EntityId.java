package com.relay.infrastructure.db.annotation;

import com.relay.infrastructure.db.converter.IdConverter;
import com.relay.infrastructure.db.generator.SnowflakeEntityIdGenerator;
import jakarta.persistence.Convert;
import org.hibernate.annotations.GenericGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Convert(converter = IdConverter.class)
@GenericGenerator(
        name = "snowFlakeId",
        type = SnowflakeEntityIdGenerator.class
)
public @interface EntityId {
}
