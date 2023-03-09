package ru.relay.infrastructure.db.annotation;

import jakarta.persistence.Convert;
import org.hibernate.annotations.GenericGenerator;
import ru.relay.infrastructure.db.converter.IdConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Convert(converter = IdConverter.class)
@GenericGenerator(name = "snowFlakeId", strategy = "ru.relay.infrastructure.db.generator.SnowflakeEntityIdGenerator")
public @interface EntityId {
}
