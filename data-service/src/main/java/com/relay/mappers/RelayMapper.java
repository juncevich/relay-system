package com.relay.mappers;

import com.relay.db.entity.RelayEntity;
import com.relay.web.model.Relay;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RelayMapper {
    RelayMapper INSTANCE = Mappers.getMapper(RelayMapper.class);

    Relay mapEntityToModel(RelayEntity relayEntity);

    List<Relay> mapEntityToModel(List<RelayEntity> relayEntity);
}
