package com.relay.web.dto;

import com.relay.web.model.Relay;

import java.util.List;

public record GetAllRelaysResponse(List<Relay> relays) {
}
