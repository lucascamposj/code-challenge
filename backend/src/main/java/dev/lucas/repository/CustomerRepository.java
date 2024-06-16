package dev.lucas.repository;

import java.util.UUID;

import dev.lucas.entity.CustomerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<CustomerEntity, UUID> {
    public CustomerEntity findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
