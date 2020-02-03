package com.relay.db.entity.location;

import com.relay.db.entity.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Peregon extends AbstractEntity {

    String name;

    @OneToOne
    Station leftStation;

    @OneToOne
    Station rightStation;

    @OneToMany
    List<Point> points;

}
