package com.idus.homework.dto;

import com.idus.homework.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDto {
    private String orderNumber;
    private long uidx;
    private String name;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public Order toEntity() {
        return Order.builder()
                .orderNumber(orderNumber)
                .uidx(uidx)
                .name(name)
                .build();
    }
}
