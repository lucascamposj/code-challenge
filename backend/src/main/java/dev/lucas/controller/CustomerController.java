package dev.lucas.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import dev.lucas.dto.CreateCustomerDTO;
import dev.lucas.entity.CustomerEntity;
import dev.lucas.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<CustomerEntity> customers = customerService.findAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@QueryParam("email") String email) {
        try {
            CustomerEntity customer = customerService.findByEmail(email);
            return Response.ok(customer).build();
        } catch (NotFoundException e) {
            return Response.status(NOT_FOUND).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CreateCustomerDTO customerDTO) {
        try {
            CustomerEntity customer = customerService.create(customerDTO);
            return Response.created(URI.create("/api/customer/" + customer.getId())).build();
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
