package com.relay.infrastructure.db.generator;

import com.relay.infrastructure.generator.SnowflakeIdGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.lang.reflect.Field;

public class SnowflakeEntityIdGenerator implements IdentifierGenerator {

    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        try {
            String id = readId(object);
            if (id != null) {
                return Long.valueOf(id);
            }
        } catch (ReflectiveOperationException var4) {
            throw new HibernateException("only support id as the primary key by now");
        }

        return SnowflakeIdGenerator.getId();
    }

    private String readId(Object object) throws ReflectiveOperationException {
        Field field = findIdField(object.getClass());
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        Object value = field.get(object);
        return value == null ? null : value.toString();
    }

    private Field findIdField(Class<?> type) {
        Class<?> current = type;
        while (current != null) {
            try {
                return current.getDeclaredField("id");
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }
        return null;
    }
}
