package com.relay.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayMapper {

    @Mapping(target = "storageId", source = "storage.id")
    @Mapping(target = "shelfId", source = "shelf.id")
    com.relay.core.model.Relay mapEntityToModel(com.relay.db.entity.items.Relay entity);

    List<com.relay.core.model.Relay> mapEntityToModel(List<com.relay.db.entity.items.Relay> entities);

    @Mapping(target = "storage", ignore = true)
    @Mapping(target = "shelf", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    com.relay.db.entity.items.Relay mapModelToEntity(com.relay.core.model.Relay model);
}
