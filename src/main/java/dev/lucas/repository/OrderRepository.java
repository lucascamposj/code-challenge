package dev.lucas.repository;

import java.util.List;
import java.util.UUID;

import dev.lucas.entity.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<Order, UUID> {
    public List<Order> findByCustomerId(UUID id) {
        return find("customer.id", id).list();
    }
}