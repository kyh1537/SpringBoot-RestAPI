package com.idus.homework.service;

import com.idus.homework.entity.Order;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    List<Order> getOrderListByUidx(long uidx);

    Page<Order> getOrderListByUidxIn(ArrayList<Long> uidxs, int page, int size);

    Page<Order> getAll(int page, int size);

    ArrayList<Order> getAllLastOrders(int page, int size);
}
