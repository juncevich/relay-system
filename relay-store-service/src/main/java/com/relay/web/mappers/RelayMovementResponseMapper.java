package com.relay.web.mappers;

import com.relay.core.model.history.RelayMovement;
import com.relay.web.model.history.RelayMovementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelayMovementResponseMapper {

    RelayMovementResponse mapRelayMovementToResponse(RelayMovement model);

    List<RelayMovementResponse> mapRelayMovementsToResponse(List<RelayMovement> models);
}
