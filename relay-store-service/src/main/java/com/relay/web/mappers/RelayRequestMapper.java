package com.relay.web.mappers;

import com.relay.core.model.Relay;
import com.relay.web.dto.CreateRelayRequest;
import com.relay.web.dto.UpdateRelayRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayRequestMapper {

    @Mapping(target = "createdAt", expression = "java(request.effectiveCreatedAt())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastCheckDate", ignore = true)
    @Mapping(target = "relayType", ignore = true)
    @Mapping(target = "placeNumber", constant = "0")
    @Mapping(target = "shelfId", ignore = true)
    Relay mapCreateRequest(CreateRelayRequest request);

    @Mapping(target = "createdAt", expression = "java(request.effectiveCreatedAt())")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "request.verificationDate", target = "lastCheckDate")
    @Mapping(target = "relayType", ignore = true)
    @Mapping(target = "placeNumber", constant = "0")
    @Mapping(target = "shelfId", ignore = true)
    Relay mapUpdateRequest(UpdateRelayRequest request, Long id);
}
