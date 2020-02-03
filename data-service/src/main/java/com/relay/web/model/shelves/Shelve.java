package com.relay.web.model.shelves;

import javax.persistence.Entity;

import com.relay.web.model.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

public class Shelve extends AbstractEntity {

    protected Integer number;
}
