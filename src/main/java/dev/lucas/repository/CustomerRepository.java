package dev.lucas.repository;

import java.util.UUID;

import dev.lucas.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {
    public Customer findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
