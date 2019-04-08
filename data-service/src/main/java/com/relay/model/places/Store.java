package com.relay.model.places;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.Relay;

import lombok.*;

@Entity
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
