package com.relay;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import com.relay.controllers.RelayController;
import com.relay.model.Relay;

public class RelayResourceProcessor implements ResourceProcessor<Resource<Relay>> {

    RepositoryEntityLinks links;

    @Override
    public Resource<Relay> process(Resource<Relay> resource) {

        Relay relay = resource.getContent();

        resource.add(
                linkTo(methodOn(RelayController.class).retrieveRelay(relay.getId().intValue()))
                        .withRel("retrieving"));

        return null;
    }
}
