package com.relay.model.places;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;

import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
class Place extends AbstractEntity {

}
