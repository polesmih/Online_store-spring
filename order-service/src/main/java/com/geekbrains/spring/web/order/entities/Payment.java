package com.geekbrains.spring.web.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    private String billId;

    @ManyToOne
    private OrderItem orderItem;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


}
