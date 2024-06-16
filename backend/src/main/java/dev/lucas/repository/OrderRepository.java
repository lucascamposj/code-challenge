package dev.lucas.repository;

import java.util.List;
import java.util.UUID;

import dev.lucas.entity.OrderEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderEntity, UUID> {
    public List<OrderEntity> findByCustomerId(UUID id) {
        return find("customer.id", id).list();
    }
}