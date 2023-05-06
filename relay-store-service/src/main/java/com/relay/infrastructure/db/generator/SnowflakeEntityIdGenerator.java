package com.relay.infrastructure.db.generator;

import com.relay.infrastructure.generator.SnowflakeIdGenerator;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class SnowflakeEntityIdGenerator implements IdentifierGenerator {

    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        try {
            String id = BeanUtils.getProperty(object, "id");
            if (id != null) {
                return Long.valueOf(id);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var4) {
            throw new HibernateException("only support id as the primary key by now");
        }

        return SnowflakeIdGenerator.getId();
    }
}
