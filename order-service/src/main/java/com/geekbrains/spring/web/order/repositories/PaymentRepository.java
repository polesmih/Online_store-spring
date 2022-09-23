package com.geekbrains.spring.web.order.repositories;

import com.geekbrains.spring.web.order.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

}
