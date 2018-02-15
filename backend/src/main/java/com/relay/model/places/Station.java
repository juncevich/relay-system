package com.relay.model.places;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.Relay;
import com.relay.model.statives.Stativ;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Station extends Place {

    @NonNull
    private String name;

//    @OneToMany(targetEntity = Relay.class)
    private List<Relay> relay;

//    @OneToMany(targetEntity = Stativ.class)
    private List<Stativ> statives;

    public Station(String name) {

        this.name = name;
    }
}
