package com.relay.db.entity.place;

import com.relay.db.entity.AbstractEntity;
import com.relay.db.entity.Shelve;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Stativ extends AbstractEntity {

    String name;

    @OneToMany
    List<Shelve> shelves;
}

