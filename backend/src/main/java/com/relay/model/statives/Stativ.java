package com.relay.model.statives;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.AbstractEntity;
import com.relay.model.shelves.Shelve;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Stativ extends AbstractEntity {

    /**
     * List of shelves
     */
    @OneToMany
    private List<Shelve> shelves;

}
