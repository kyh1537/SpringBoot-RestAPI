package com.idus.homework.controller;

import com.idus.homework.config.security.JwtTokenProvider;
import com.idus.homework.dto.UserDto;
import com.idus.homework.entity.User;
import com.idus.homework.model.Response;
import com.idus.homework.model.Token;
import com.idus.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     */
    @PostMapping("/sign-up")
    public Response signUp(@RequestBody UserDto userDto) {
        try {
            userService.signUpUser(userDto);
            return new Response("success", "회원가입 완료", null);
        } catch (InputMismatchException e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public Response login(@RequestBody Map<String, String> map) {
        try {
            User user = userService.login(map.get("mail"), map.get("pw"));
            Token token = Token.builder()
                    .idx(user.getIdx())
                    .mail(user.getMail())
                    .token(jwtTokenProvider.createToken(user.getMail(), user.getRoles()))
                    .build();
            return new Response("success", "로그인 성공", token);
        } catch (InputMismatchException e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * idx 기준 단일 회원 상세 정보 조회
     */
    @GetMapping("/user/idx")
    public Response userInfoByIdx(@RequestParam("q") long idx) {
        try {
            User user = userService.getUserByIdx(idx);
            return new Response("success", "성공", new UserDto(user));
        } catch (UsernameNotFoundException e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    /**
     * 메일 기준 단일 회원 상세 정보 조회
     */
    @GetMapping("/user/mail")
    public Response userInfoByMail(@RequestParam("q") String mail) {
        try {
            User user = userService.getUserByMail(mail);
            return new Response("success", "성공", new UserDto(user));
        } catch (UsernameNotFoundException e) {
            return new Response("error", e.getMessage(), null);
        }
    }
}