package com.relay.db.mappers;

import com.relay.db.entity.storage.Shelf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShelfMapper {

    @Mapping(target = "storageId", source = "storage.id")
    com.relay.core.model.storage.Shelf mapEntityToModel(Shelf entity);

    List<com.relay.core.model.storage.Shelf> mapEntityToModel(List<Shelf> entities);

    @Mapping(target = "storage", ignore = true)
    Shelf mapModelToEntity(com.relay.core.model.storage.Shelf model);
}