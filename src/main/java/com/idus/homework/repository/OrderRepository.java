package com.idus.homework.repository;


import com.idus.homework.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface OrderRepository extends JpaRepository<Order, Long> {
    ArrayList<Order> findByUidx(long uidx);

    Page<Order> findByUidxIn(ArrayList<Long> uidxs, Pageable pageable);

    Page<Order> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM(SELECT * FROM user_orders WHERE (uidx,reg_date) IN (SELECT uidx, MAX(reg_date) as reg_date FROM user_orders GROUP BY uidx) ORDER BY reg_date DESC) t GROUP BY t.uidx LIMIT ?,?", nativeQuery = true)
    ArrayList<Order> findAllByLastOrder(int page, int size);
}

