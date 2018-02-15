package com.relay.model.places;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
public class Place extends AbstractEntity {

}
