package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
public class Shelve extends AbstractEntity {

}
