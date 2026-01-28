package com.relay.db.mappers;

import com.relay.core.model.storage.RelayCabinetModel;
import com.relay.core.model.storage.StandModel;
import com.relay.core.model.storage.WarehouseModel;
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
    WarehouseModel mapWarehouseEntityToModel(Warehouse entity);

    List<WarehouseModel> mapWarehouseEntityToModel(List<Warehouse> entities);

    @Mapping(target = "location", ignore = true)
    Warehouse mapModelToWarehouseEntity(WarehouseModel model);

    @Mapping(target = "locationId", source = "location.id")
    StandModel mapStandEntityToModel(Stand entity);

    List<StandModel> mapStandEntityToModel(List<Stand> entities);

    @Mapping(target = "location", ignore = true)
    Stand mapModelToStandEntity(StandModel model);

    @Mapping(target = "locationId", source = "location.id")
    RelayCabinetModel mapRelayCabinetEntityToModel(RelayCabinet entity);

    List<RelayCabinetModel> mapRelayCabinetEntityToModel(List<RelayCabinet> entities);

    @Mapping(target = "location", ignore = true)
    RelayCabinet mapModelToRelayCabinetEntity(RelayCabinetModel model);
}
