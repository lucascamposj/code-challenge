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
@Table(name = "orders")
public class Order extends PanacheEntityBase {
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
    @Enumerated(EnumType.ORDINAL)
    public PaymentMethodType paymentMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public OrderStatusType status;

    @Column
    @CreationTimestamp
    public ZonedDateTime createdAt;

    @Column
    @UpdateTimestamp
    public ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    public Customer customer;
}
