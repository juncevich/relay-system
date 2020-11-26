package com.relay.db.entity;

import com.relay.db.entity.place.Container;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelayAddress {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Container container;

    private Integer row;

    private Integer place;
}
