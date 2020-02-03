package com.relay.db.entity.place;

import com.relay.db.entity.AbstractEntity;
import com.relay.db.entity.Shelve;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class RelayCase extends AbstractEntity {

    String name;

    @OneToMany
    List<Shelve> shelves;
}

