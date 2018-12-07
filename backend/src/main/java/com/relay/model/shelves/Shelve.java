package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Shelve extends AbstractEntity {

    protected Integer number;
}
