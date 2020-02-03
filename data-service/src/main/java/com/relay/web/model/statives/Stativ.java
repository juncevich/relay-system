package com.relay.web.model.statives;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.web.model.AbstractEntity;
import com.relay.web.model.shelves.Shelve;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Stativ extends AbstractEntity {

    private Integer number;

    /**
     * List of shelves
     */
    @OneToMany
    private List<Shelve> shelves;

}
