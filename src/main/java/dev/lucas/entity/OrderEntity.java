package dev.lucas.entity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "customer_order")
public class OrderEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    public float price;

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public List<String> items;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public PaymentMethodEnum paymentMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public OrderStatusEnum status;

    @Column
    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    public ZonedDateTime createdAt;

    @Column
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    public ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    public CustomerEntity customer;
}
