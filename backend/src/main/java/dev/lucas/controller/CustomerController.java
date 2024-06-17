package dev.lucas.controller;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import dev.lucas.dto.CreateCustomerDTO;
import dev.lucas.entity.CustomerEntity;
import dev.lucas.service.CustomerService;
import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/v1/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Retry(maxRetries = 4)
    @Timeout(1000) // in ms
    @CircuitBreaker(requestVolumeThreshold = 4) // failureRatio is 0.5, delay is 5s
    @RateLimit(value = 5, window = 1, windowUnit = ChronoUnit.SECONDS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@QueryParam("email") String email) {
        if (email != null) {
            try {
                CustomerEntity customer = customerService.findByEmail(email);
                return Response.ok(customer).build();
            } catch (NotFoundException e) {
                return Response.status(NOT_FOUND).build();
            }
        }
        List<CustomerEntity> customers = customerService.findAllCustomers();
        return Response.ok(customers).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CreateCustomerDTO customerDTO) {
        try {
            CustomerEntity customer = customerService.create(customerDTO);
            return Response.ok(customer).status(CREATED).build();
        } catch (BadRequestException e) {
            return Response.status(BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id) {
        CustomerEntity customer = customerService.findById(UUID.fromString(id));
        return Response.ok(customer).build();
    }
}
