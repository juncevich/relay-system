package com.relay.web.model.statives;

import com.relay.web.model.shelves.Shelve;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Stativ {

    private Integer number;

    /**
     * List of shelves
     */
    @OneToMany
    private List<Shelve> shelves;

}
