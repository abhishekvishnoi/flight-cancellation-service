package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


import java.util.concurrent.CompletionStage;

@Path("/cancelFlight")
public class FlightCancellationResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("flightNumber") String flightNumber) {

        System.out.println(flightNumber);
        CompletionStage<Void> ack = priceEmitter.send(flightNumber);
        return "Flight " + flightNumber +" has been Cancelled ";
    }

    @Inject
    @Channel("flight-cancellation-topic")
    Emitter<String> priceEmitter;

}
