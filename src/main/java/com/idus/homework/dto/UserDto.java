package com.idus.homework.dto;

import com.idus.homework.entity.Order;
import com.idus.homework.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Long idx;
    private String name;
    private String nickName;
    private String pw;
    private String phoneNumber;
    private String mail;
    private String sex;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private List<String> roles;
    private List<Order> orders = new ArrayList<>();

    public UserDto(User user) {
        this.idx = user.getIdx();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.phoneNumber = user.getPhoneNumber();
        this.mail = user.getMail();
        this.sex = user.getSex();
        this.roles = user.getRoles();
        this.regDate = user.getRegDate();
        this.updateDate = user.getUpdateDate();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .nickName(nickName)
                .pw(pw)
                .phoneNumber(phoneNumber)
                .mail(mail)
                .sex(sex)
                .roles(roles)
                .build();
    }
}

