package com.relay.web.mappers;

import com.relay.web.model.Relay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayResponseMapper {

    @Mapping(source = "lastCheckDate", target = "verificationDate")
    Relay mapToResponse(com.relay.core.model.Relay model);

    List<Relay> mapToResponseList(List<com.relay.core.model.Relay> models);
}
