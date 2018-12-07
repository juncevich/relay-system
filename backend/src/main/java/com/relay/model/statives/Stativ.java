package com.relay.model.statives;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.AbstractEntity;
import com.relay.model.shelves.Shelve;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stativ extends AbstractEntity {

    private final Integer number;

    /**
     * List of shelves
     */
    @OneToMany
    private List<Shelve> shelves;

}
