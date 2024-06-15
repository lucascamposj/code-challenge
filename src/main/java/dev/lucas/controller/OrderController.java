package dev.lucas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.dto.UpdateOrderDTO;
import dev.lucas.entity.Order;
import dev.lucas.service.OrderService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/api/order")
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> findAll() {

        List<Order> orders = new ArrayList<>();
        try {
            orders = orderService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Order create(CreateOrderDTO orderDto) {
        return orderService.create(orderDto);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Order update(@PathParam("id") String id, UpdateOrderDTO dto) {
        return orderService.update(UUID.fromString(id), dto.getStatus());
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> findByCustomerId(@QueryParam("customerId") String customerId) {
        return orderService.findByCustomerId(UUID.fromString(customerId));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order findById(@PathParam("id") String id) {
        return orderService.findById(UUID.fromString(id));
    }
}
