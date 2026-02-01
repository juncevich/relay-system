package com.relay.web.mappers;

import com.relay.core.model.storage.RelayCabinet;
import com.relay.core.model.storage.Shelf;
import com.relay.core.model.storage.Stand;
import com.relay.core.model.storage.Warehouse;
import com.relay.web.model.storage.RelayCabinetResponse;
import com.relay.web.model.storage.ShelfResponse;
import com.relay.web.model.storage.StandResponse;
import com.relay.web.model.storage.WarehouseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorageResponseMapper {

    ShelfResponse mapShelfToResponse(Shelf model);

    List<ShelfResponse> mapShelvesToResponse(List<Shelf> models);

    WarehouseResponse mapWarehouseToResponse(Warehouse model);

    List<WarehouseResponse> mapWarehousesToResponse(List<Warehouse> models);

    StandResponse mapStandToResponse(Stand model);

    List<StandResponse> mapStandsToResponse(List<Stand> models);

    RelayCabinetResponse mapRelayCabinetToResponse(RelayCabinet model);

    List<RelayCabinetResponse> mapRelayCabinetsToResponse(List<RelayCabinet> models);
}
