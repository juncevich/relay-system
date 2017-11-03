package com.relay.model.statives;

import java.util.List;

import javax.persistence.Entity;

import com.relay.model.AbstractEntity;
import com.relay.model.shelves.Shelve;

import lombok.Data;

@Entity
@Data
public class Stativ extends AbstractEntity {

    private List<Shelve> shelves;

}
