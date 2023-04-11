package com.felixmcd.orderservice.service;


import com.felixmcd.orderservice.dto.OrderRequest;
import com.felixmcd.orderservice.model.Order;
import com.felixmcd.orderservice.model.orderLineItems;
import com.felixmcd.orderservice.dto.OrderLineItemsDto;
import com.felixmcd.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional //automatically spring creates and commit the transactions
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        List<orderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private orderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        orderLineItems orderLineItems = new orderLineItems();
        orderLineItems.setPrice(orderLineItems.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItems;
    }


}
