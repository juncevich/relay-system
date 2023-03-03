package ru.relay.infrastructure.db;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import ru.relay.infrastructure.id.SnowflakeId;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class SnowflakeIdGenerator implements IdentifierGenerator {

    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        try {
            String id = BeanUtils.getProperty(object, "id");
            if (id != null) {
                return Long.valueOf(id);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var4) {
            throw new HibernateException("only support id as the primary key by now");
        }

        return SnowflakeId.getId();
    }
}
