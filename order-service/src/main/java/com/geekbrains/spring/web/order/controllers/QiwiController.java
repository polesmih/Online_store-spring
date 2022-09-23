package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.order.dto.QiwiResponse;
import com.geekbrains.spring.web.order.entities.Order;
import com.geekbrains.spring.web.order.services.QiwiService;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import com.qiwi.billpayments.sdk.model.out.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/paypal")
@RequiredArgsConstructor
@Slf4j
public class QiwiController {
    private final QiwiService qiwiService;
    private String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6IjE1YmpsNi0wMCIsInVzZXJfaWQiOiI3OTIxNzgyMzc2NCIsInNlY3JldCI6ImQwYmM5NTgyMzgxMGFjNjZhZGU3ODQ2OTg0OGI1YmIzNDNjZjFiNDEwZjUxNTAyZWVhNjk5NjNjODAxZmZjYmEifX0=";
    private BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);

    //Добавить статусы "Оплачен\не оплачен" в наш Ордер
    // В зависимости от успеха операции, менять этот статус
    // Оплаченные заказы не долны быть оплачены дважды
    @GetMapping("/create/{orderId}")
    public QiwiResponse createOrder(@PathVariable Long orderId) throws URISyntaxException {
        BillResponse response = client.createBill(qiwiService.createOrderRequest(orderId));
        log.info("resp = {}", response);
        return new QiwiResponse(response.getPayUrl(), response.getBillId());
    }

//    @PostMapping("/capture/{billId}")
//    public ResponseEntity<?> captureOrder(@PathVariable String billId) throws IOException {
//        BillResponse response = client.getBillInfo(billId);
//        if("COMPLETED".equals(response.getStatus())) {
//            //ToDo сделать обработку статуса
//        }
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    // попытка реализации контроля оплаты
    @GetMapping("/checkPayment")
    public String checkBill(@RequestParam("orderId") Long orderId, @RequestParam("billId") String billId) {
        qiwiService.checkBill(billId);
        return "redirect:/orders" + orderId;
    }




}
