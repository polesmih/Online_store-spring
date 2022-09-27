package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.order.dto.QiwiResponse;
import com.geekbrains.spring.web.order.services.OrderService;
import com.geekbrains.spring.web.order.services.QiwiService;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/qiwi")
@RequiredArgsConstructor
@Slf4j
public class QiwiController {

    private final OrderService orderService;
    private final QiwiService qiwiService;
    private final BillPaymentClient billPaymentClient;

    @GetMapping("/create/{orderId}")
    public QiwiResponse createOrder(@PathVariable Long orderId) throws URISyntaxException {
        BillResponse response = billPaymentClient.createBill(qiwiService.createBill(orderId));
        log.info("resp = {}", response);
        return new QiwiResponse(response.getPayUrl(), response.getBillId());
    }

    @PostMapping("/capture/{billId}")
    public ResponseEntity<?> captureOrder(@PathVariable String billId) throws IOException {
        BillResponse response = billPaymentClient.getBillInfo(billId);
        if("COMPLETED".equals(response.getStatus())) {
            System.out.println("Заказ № " + response.getStatus());
            qiwiService.makePaid(billId);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
