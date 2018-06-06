package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Shelve extends AbstractEntity {

}
