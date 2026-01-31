package com.relay.db.mappers;

import com.relay.db.entity.storage.RelayCabinet;
import com.relay.db.entity.storage.Stand;
import com.relay.db.entity.storage.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorageMapper {

    @Mapping(target = "locationId", source = "location.id")
    com.relay.core.model.storage.Warehouse mapWarehouseEntityToModel(Warehouse entity);

    List<com.relay.core.model.storage.Warehouse> mapWarehouseEntityToModel(List<Warehouse> entities);

    @Mapping(target = "location", ignore = true)
    Warehouse mapModelToWarehouseEntity(com.relay.core.model.storage.Warehouse model);

    @Mapping(target = "locationId", source = "location.id")
    com.relay.core.model.storage.Stand mapStandEntityToModel(Stand entity);

    List<com.relay.core.model.storage.Stand> mapStandEntityToModel(List<Stand> entities);

    @Mapping(target = "location", ignore = true)
    Stand mapModelToStandEntity(com.relay.core.model.storage.Stand model);

    @Mapping(target = "locationId", source = "location.id")
    com.relay.core.model.storage.RelayCabinet mapRelayCabinetEntityToModel(RelayCabinet entity);

    List<com.relay.core.model.storage.RelayCabinet> mapRelayCabinetEntityToModel(List<RelayCabinet> entities);

    @Mapping(target = "location", ignore = true)
    RelayCabinet mapModelToRelayCabinetEntity(com.relay.core.model.storage.RelayCabinet model);
}
