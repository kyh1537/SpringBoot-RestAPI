package com.idus.homework.service.impl;

import com.idus.homework.entity.Order;
import com.idus.homework.repository.OrderRepository;
import com.idus.homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrderListByUidx(long uidx) {
        return orderRepository.findByUidx(uidx);
    }

    @Override
    public Page<Order> getOrderListByUidxIn(ArrayList<Long> uidxs, int page, int size) {
        return orderRepository.findByUidxIn(uidxs, PageRequest.of(page, size));
    }

    @Override
    public Page<Order> getAll(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public ArrayList<Order> getAllLastOrders(int page, int size) {
        return orderRepository.findAllByLastOrder(page * size, size);
    }
}

