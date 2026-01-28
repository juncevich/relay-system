package com.relay.db.mappers;

import com.relay.core.model.history.RelayMovementModel;
import com.relay.db.entity.history.RelayMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayMovementMapper {

    @Mapping(target = "relayId", source = "relay.id")
    @Mapping(target = "relaySerialNumber", source = "relay.serialNumber")
    @Mapping(target = "fromStorageId", source = "fromStorage.id")
    @Mapping(target = "toStorageId", source = "toStorage.id")
    RelayMovementModel mapEntityToModel(RelayMovement entity);

    List<RelayMovementModel> mapEntityToModel(List<RelayMovement> entities);

    @Mapping(target = "relay", ignore = true)
    @Mapping(target = "fromStorage", ignore = true)
    @Mapping(target = "toStorage", ignore = true)
    RelayMovement mapModelToEntity(RelayMovementModel model);
}