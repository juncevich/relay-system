package com.relay.core.mappers;

import com.relay.web.model.Relay;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-11T17:09:20+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class RelayMapperImpl implements RelayMapper {

    @Override
    public Relay mapEntityToModel(com.relay.db.entity.items.Relay relayEntity) {
        if ( relayEntity == null ) {
            return null;
        }

        Relay relay = new Relay();

        relay.setDateOfManufacture( relayEntity.getCreationDate() );
        relay.setSerialNumber( relayEntity.getSerialNumber() );

        return relay;
    }

    @Override
    public List<Relay> mapEntityToModel(List<com.relay.db.entity.items.Relay> relayEntity) {
        if ( relayEntity == null ) {
            return null;
        }

        List<Relay> list = new ArrayList<Relay>( relayEntity.size() );
        for ( com.relay.db.entity.items.Relay relay : relayEntity ) {
            list.add( mapEntityToModel( relay ) );
        }

        return list;
    }

    @Override
    public com.relay.db.entity.items.Relay mapModelToEntity(Relay relay) {
        if ( relay == null ) {
            return null;
        }

        com.relay.db.entity.items.Relay relay1 = new com.relay.db.entity.items.Relay();

        relay1.setSerialNumber( relay.getSerialNumber() );

        return relay1;
    }
}
