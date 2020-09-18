package com.relay.db.entity.location;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Long id;


}
