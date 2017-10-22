package com.relaysystem.ms.relayexchangeservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExchangeValue {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "relay_from")
    private String from;
    @Column(name = "relay_to")
    private String to;
    @Column(name = "control_result")
    private String controlResult;
    private int port;

    public ExchangeValue(Long id, String from, String to, String controlResult) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.controlResult = controlResult;
    }
}
