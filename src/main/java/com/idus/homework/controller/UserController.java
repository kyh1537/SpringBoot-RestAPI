package com.idus.homework.controller;

import com.idus.homework.config.security.JwtTokenProvider;
import com.idus.homework.dto.UserDto;
import com.idus.homework.entity.User;
import com.idus.homework.model.Response;
import com.idus.homework.model.Token;
import com.idus.homework.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Api(value = "1. UserController")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원 가입 API", notes = "가입 정보를 담아주면 각 항목에 대해 유효성 체크 후에 회원 가입이 완료됩니다.")
    @PostMapping("/sign-up")
    public Response signUp(@ApiParam(value = "userDto 값", required = true) @RequestBody UserDto userDto) {
        try {
            userService.signUpUser(userDto);
            return new Response("success", "회원가입 완료", null);
        } catch (InputMismatchException e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    @ApiOperation(value = "로그인 API", notes = "메일과 패스워드를 입력하면 로그인 및 토큰이 발급됩니다.")
    @PostMapping("/login")
    public Response login(@ApiParam(value = "메일, 패스워드", required = true) @RequestBody Map<String, String> map) {
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


    @ApiOperation(value = "idx 기준 단일 회원 상세 정보 조회 API", notes = "검색하고자 하는 유저의 idx를 넘겨주면 해당 유저의 정보를 보내줍니다.")
    @GetMapping("/user/idx")
    public Response userInfoByIdx(@ApiParam(value = "유저 인덱스", required = true, example = "1") @RequestParam("q") long idx) {
        try {
            User user = userService.getUserByIdx(idx);
            return new Response("success", "성공", new UserDto(user));
        } catch (UsernameNotFoundException e) {
            return new Response("error", e.getMessage(), null);
        }
    }

    @ApiOperation(value = "메일 기준 단일 회원 상세 정보 조회 API", notes = "검색하고자 하는 유저의 메일을 넘겨주면 해당 유저의 정보를 보내줍니다.")
    @GetMapping("/user/mail")
    public Response userInfoByMail(@ApiParam(value = "메일", required = true, example = "kyh1537@naver.com") @RequestParam("q") String mail) {
        try {
            User user = userService.getUserByMail(mail);
            return new Response("success", "성공", new UserDto(user));
        } catch (UsernameNotFoundException e) {
            return new Response("error", e.getMessage(), null);
        }
    }
}