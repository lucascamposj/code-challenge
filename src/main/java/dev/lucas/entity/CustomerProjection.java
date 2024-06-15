package dev.lucas.entity;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

record OrderProjection(
        Long id,
        String name,
        String image,
        String program,
        @ProjectedFieldName("customer.id") String customerId) {
}
