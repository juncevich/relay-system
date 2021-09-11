package com.relay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "history")
public interface HistoryServiceClient {

    @GetMapping("/history/{recordId}")
    List<String> findByDepartment(@PathVariable("recordId") String recordId);
}
