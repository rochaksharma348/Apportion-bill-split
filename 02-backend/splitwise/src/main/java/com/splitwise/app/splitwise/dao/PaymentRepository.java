package com.splitwise.app.splitwise.dao;

import com.splitwise.app.splitwise.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "payments", path = "payments")
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
