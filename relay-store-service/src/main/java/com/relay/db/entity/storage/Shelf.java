package com.relay.db.entity.storage;

import com.relay.infrastructure.db.annotation.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shelf {

    @Id
    @EntityId
    @GeneratedValue(generator = "snowFlakeId")
    private Long id;

    private int number;

    private int capacity;

    @ManyToOne(optional = false)
    private Storage storage;
}