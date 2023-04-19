package com.relay.db.entity;

import com.relay.db.entity.place.Container;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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
