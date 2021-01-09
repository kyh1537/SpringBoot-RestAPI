package com.idus.homework.controller;

import com.idus.homework.entity.Order;
import com.idus.homework.entity.User;
import com.idus.homework.model.Response;
import com.idus.homework.service.OrderService;
import com.idus.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    /**
     * idx 기준으로 단일 회원 주문 목록 조회
     */
    @GetMapping("/order/idx")
    public Response orderByIdx(@RequestParam("q") long idx) {
        try {
            return new Response("success", "성공", orderService.getOrderListByUidx(idx));
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * mail 기준으로 단일 회원 주문 목록 조회
     * mail을 로그인 id(유니크) 기준으로 잡았기에 중복 불가 따라서 단일 회원 주문
     */
    @GetMapping("/order/mail")
    public Response orderByMail(@RequestParam("q") String mail) {
        try {
            User user = userService.getUserByMail(mail);
            return new Response("success", "성공", user.getOrders());
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * 이름 기준으로 검색
     */
    @GetMapping("/order/name")
    public Response orderByName(@RequestParam("q") String name,
                                @RequestParam("page") int page,
                                @RequestParam("size") int size) {
        try {
            ArrayList<Long> idx = userService.getUserByName(name);
            Page<Order> orders = orderService.getOrderListByUidxIn(idx, page, size);
            return new Response("success", "성공", orders.getContent());
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * 모든 회원의 order 리스트 검색
     */
    @GetMapping("/order")
    public Response orderList(@RequestParam("page") int page,
                              @RequestParam("size") int size) {
        try {
            Page<Order> orders = orderService.getAll(page, size);
            return new Response("success", "성공", orders.getContent());
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * 각 회원의 마지막 주문 정보
     */
    @GetMapping("/order/last")
    public Response orderLastList(@RequestParam("page") int page,
                                  @RequestParam("size") int size) {
        try {
            ArrayList<Order> orders = orderService.getAllOrderBy(page, size);
            return new Response("success", "성공", orders);
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }
}
