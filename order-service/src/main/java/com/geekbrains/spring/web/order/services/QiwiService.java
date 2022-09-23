package com.geekbrains.spring.web.order.services;

import com.geekbrains.spring.web.order.entities.Order;
import com.geekbrains.spring.web.order.entities.Payment;
import com.geekbrains.spring.web.order.entities.PaymentStatus;
import com.geekbrains.spring.web.order.repositories.PaymentRepository;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.BillStatus;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QiwiService {
    private final OrderService orderService;
    private String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6IjE1YmpsNi0wMCIsInVzZXJfaWQiOiI3OTIxNzgyMzc2NCIsInNlY3JldCI6ImQwYmM5NTgyMzgxMGFjNjZhZGU3ODQ2OTg0OGI1YmIzNDNjZjFiNDEwZjUxNTAyZWVhNjk5NjNjODAxZmZjYmEifX0=";
    private BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);
    private final PaymentRepository paymentRepository;

    public CreateBillInfo createOrderRequest(Long orderId){
        Order order = orderService.findOrderById(orderId);

        CreateBillInfo billInfo = new CreateBillInfo(
                UUID.randomUUID().toString(),
                new MoneyAmount(
                        BigDecimal.valueOf(1),
                        Currency.getInstance("RUB")
                ),
                "comment",
                ZonedDateTime.now().plusDays(45),
                new Customer(
                        "mail@example.org",
                        UUID.randomUUID().toString(),
                        "79123456789"
                ),
                "http://localhost:3000/front/#!/store"
        );
        return billInfo;
    }

    // попытка реализации контроля оплаты
    public void checkBill(String billId) {
        Payment payment = paymentRepository.findById(billId).orElseThrow(() -> new MessagingException("Bill not found"));
        BillResponse response = client.getBillInfo(billId);
        if(response.getStatus().getValue().equals(BillStatus.PAID)) {
            payment.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);
        } else if (!response.getStatus().getValue().equals(BillStatus.WAITING)) {
            paymentRepository.delete(payment);

        }

    }

}
