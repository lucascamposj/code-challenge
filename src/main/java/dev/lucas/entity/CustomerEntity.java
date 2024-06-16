package dev.lucas.entity;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue()
    public UUID id;

    @Column(nullable = false)
    @NotEmpty
    public String name;

    @Column(nullable = false, unique = true)
    @NotEmpty
    public String email;

    @Column
    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    public ZonedDateTime createdAt;
}
