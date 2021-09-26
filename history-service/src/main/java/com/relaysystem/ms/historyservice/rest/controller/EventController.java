package com.relaysystem.ms.historyservice.rest.controller;

import com.relaysystem.ms.historyservice.core.service.EventService;
import com.relaysystem.ms.historyservice.rest.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/")
    public List<EventDto> findAllEvents() {
        eventService.
    }
}
