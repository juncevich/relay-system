package com.relay.model.places;

import org.springframework.data.mongodb.core.mapping.Document;

import com.relay.model.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
class Place extends AbstractEntity {

}
