package com.relay.mappers;

import com.relay.db.entity.RelayEntity;
import com.relay.web.model.Relay;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayMapper {
    RelayMapper INSTANCE = Mappers.getMapper(RelayMapper.class);

    Relay mapEntityToModel(RelayEntity relayEntity);

    List<Relay> mapEntityToModel(List<RelayEntity> relayEntity);

    RelayEntity mapModelToEntity(Relay relay);
}
