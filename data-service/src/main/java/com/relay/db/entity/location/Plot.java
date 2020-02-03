package com.relay.db.entity.location;

import com.relay.db.entity.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plot extends AbstractEntity {

    @OneToMany
    List<Station> stations;

    @OneToMany
    List<Peregon> peregons;
}
