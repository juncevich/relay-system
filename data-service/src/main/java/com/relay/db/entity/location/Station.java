package com.relay.db.entity.location;

import com.relay.db.entity.AbstractEntity;
import com.relay.db.entity.place.Stativ;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;


@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Station extends AbstractEntity {

    String name;

    @OneToOne(fetch = FetchType.LAZY)
    Peregon leftPeregon;

    @OneToOne(fetch = FetchType.LAZY)
    Peregon rightPeregon;

    @OneToMany(fetch = FetchType.LAZY)
    List<Stativ> statives;

    @OneToMany(fetch = FetchType.LAZY)
    List<Pereezd> pereezdList;
}