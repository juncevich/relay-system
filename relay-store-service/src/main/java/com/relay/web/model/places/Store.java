package com.relay.web.model.places;

import com.relay.web.model.Relay;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Store extends Place {

    /**
     * Relay list
     */
    @OneToMany
    private List<Relay> relay;
}
