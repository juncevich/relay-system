package com.relay.db.mappers;

import com.relay.web.model.Relay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayMapper {

    RelayMapper INSTANCE = Mappers.getMapper(RelayMapper.class);

    @Mapping(source = "createdAt",
            target = "createdAt")
    Relay mapEntityToModel(com.relay.db.entity.items.Relay relayEntity);


    List<Relay> mapEntityToModel(List<com.relay.db.entity.items.Relay> relayEntity);

    com.relay.db.entity.items.Relay mapModelToEntity(Relay relay);
}
