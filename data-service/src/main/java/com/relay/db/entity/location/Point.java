package com.relay.db.entity.location;

import com.relay.db.entity.AbstractEntity;
import com.relay.db.entity.place.RelayCase;
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
public class Point extends AbstractEntity {

    String name;

    @OneToMany
    List<RelayCase> cases;

    @OneToOne(fetch = FetchType.LAZY)
    Point leftPoint;

    @OneToOne(fetch = FetchType.LAZY)
    Point rightPoint;
}
