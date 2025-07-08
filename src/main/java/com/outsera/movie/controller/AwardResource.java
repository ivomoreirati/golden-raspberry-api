package com.outsera.movie.controller;

import com.outsera.movie.dto.AwardResponse;
import com.outsera.movie.service.AwardService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/awards")
public class AwardResource {

    @Inject
    AwardService awardService;

    @GET
    @Path("/intervals/{minAwards}")
    @Produces(MediaType.APPLICATION_JSON)
    public AwardResponse getIntervals(@jakarta.ws.rs.PathParam("minAwards") int minAwards) {
        return awardService.calculateAwardIntervals(minAwards);
    }
}