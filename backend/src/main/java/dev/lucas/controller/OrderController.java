package dev.lucas.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import dev.lucas.dto.CreateOrderDTO;
import dev.lucas.dto.UpdateOrderDTO;
import dev.lucas.entity.OrderEntity;
import dev.lucas.service.OrderService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/order")
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<OrderEntity> orders = orderService.findAll();
        return Response.ok(orders).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CreateOrderDTO orderDto) {
        try {
            OrderEntity entity = orderService.create(orderDto);
            return Response.created(URI.create("/api/order/" + entity.getId())).build();
        } catch (BadRequestException e) {
            return Response.status(BAD_REQUEST).build();
        }
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") String id, UpdateOrderDTO dto) {
        try {
            OrderEntity order = orderService.update(UUID.fromString(id), dto);
            return Response.ok(order).build();
        } catch (NotFoundException e) {
            return Response.status(NOT_FOUND).build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCustomerId(@QueryParam("customerId") String customerId) {
        try {
            List<OrderEntity> orders = orderService.findByCustomerId(UUID.fromString(customerId));
            return Response.ok(orders).build();
        } catch (NotFoundException e) {
            return Response.status(NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id) {
        try {
            OrderEntity order = orderService.findById(UUID.fromString(id));
            return Response.ok(order).build();
        } catch (NotFoundException e) {
            return Response.status(NOT_FOUND).build();
        }
    }
}
