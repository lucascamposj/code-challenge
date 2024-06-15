package dev.lucas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.lucas.entity.Customer;
import dev.lucas.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();
        try {
            customers = customerService.findAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findByEmail(@QueryParam("email") String email) {
        return customerService.findByEmail(email);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Customer create(Customer customer) {
        customerService.create(customer);
        return customer;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findById(@PathParam("id") String id) {
        return customerService.findById(UUID.fromString(id));
    }
}
