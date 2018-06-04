package com.relay.model.places;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
class Place extends AbstractEntity {

}
