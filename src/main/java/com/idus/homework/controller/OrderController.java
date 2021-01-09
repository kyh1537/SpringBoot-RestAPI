package com.idus.homework.controller;

import com.idus.homework.entity.Order;
import com.idus.homework.entity.User;
import com.idus.homework.model.Response;
import com.idus.homework.service.OrderService;
import com.idus.homework.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@Api(value = "2. OrderController")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @ApiOperation(value = "idx 기준으로 단일 회원 주문 목록 조회", notes = "idx 정보를 주면 해당 유저의 주문 목록을 리턴")
    @GetMapping("/order/idx")
    public Response orderByIdx(@ApiParam(value = "유저 인덱스", required = true) @RequestParam("q") long idx) {
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
    @ApiOperation(value = "메일 기준으로 단일 회원 주문 목록 조회", notes = "메일 정보를 주면 해당 유저의 주문 목록을 리턴")
    @GetMapping("/order/mail")
    public Response orderByMail(@ApiParam(value = "유저 메일", required = true) @RequestParam("q") String mail) {
        try {
            User user = userService.getUserByMail(mail);
            return new Response("success", "성공", user.getOrders());
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    @ApiOperation(value = "이름 기준으로 단일 회원 주문 목록 조회", notes = "이름 정보를 주면 해당 이름 유저들의 주문 목록을 리턴")
    @GetMapping("/order/name")
    public Response orderByName(@ApiParam(value = "이름", required = true) @RequestParam(value = "q", defaultValue = "") String name,
                                @ApiParam(value = "페이지") @RequestParam(value = "page", defaultValue = "0") int page,
                                @ApiParam(value = "페이지 사이즈") @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            ArrayList<Long> idx = userService.getUserByName(name);
            Page<Order> orders = orderService.getOrderListByUidxIn(idx, page, size);
            return new Response("success", "성공", orders.getContent());
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    @ApiOperation(value = "모든 회원 주문 목록 조회", notes = "페이지와 사이즈를 정해주면 해당 페이지의 사이즈만큼만 넘겨준다.")
    @GetMapping("/order")
    public Response orderList(@ApiParam(value = "페이지") @RequestParam(value = "page", defaultValue = "0") int page,
                              @ApiParam(value = "페이지 사이즈") @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Page<Order> orders = orderService.getAll(page, size);
            return new Response("success", "성공", orders.getContent());
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    @ApiOperation(value = "각각 회원의 마지막 주문 정보", notes = "회원별 마지막 주문 정보만 페이지의 사이즈만큼 넘겨준다.")
    @GetMapping("/order/last")
    public Response orderLastList(@ApiParam(value = "페이지") @RequestParam(value = "page", defaultValue = "0") int page,
                                  @ApiParam(value = "페이지 사이즈") @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            ArrayList<Order> orders = orderService.getAllOrderBy(page, size);
            return new Response("success", "성공", orders);
        } catch (Exception e) {
            return new Response("error", e.getMessage(), null);
        }
    }
}
