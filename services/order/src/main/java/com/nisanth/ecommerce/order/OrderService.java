package com.nisanth.ecommerce.order;

import com.nisanth.ecommerce.customer.CustomerClient;
import com.nisanth.ecommerce.exception.BusinessException;
import com.nisanth.ecommerce.kafka.OrderConfirmation;
import com.nisanth.ecommerce.kafka.OrderProducer;
import com.nisanth.ecommerce.orderLine.OrderLineRequest;
import com.nisanth.ecommerce.orderLine.OrderLineService;
import com.nisanth.ecommerce.payment.PaymentClient;
import com.nisanth.ecommerce.payment.PaymentRequest;
import com.nisanth.ecommerce.product.ProductClient;
import com.nisanth.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient; // to start payment process

    public Integer createOrder(OrderRequest request) {

        // check the customer
        var customer=this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("Cannot create order::No customer exists with the provided ID"));


        // purchase the product-->product microservices(Rest Template)
        var purchasedProducts=this.productClient.purchaseProducts(request.products());


        // persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        // saving the order
        for(PurchaseRequest purchaseRequest:request.products())
        {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    ));

        }



        // start payment process
        // get data from Payment Request
        var paymentRequest=new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer

        );
        paymentClient.requestOrderPayment(paymentRequest);



        //send the order confirmation --> notification-microservices(kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("No Order found with provided ID: %d",orderId)));
    }
}
