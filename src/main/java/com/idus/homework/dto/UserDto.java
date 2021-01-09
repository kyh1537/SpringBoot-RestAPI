package com.idus.homework.dto;

import com.idus.homework.entity.Order;
import com.idus.homework.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    @ApiModelProperty(hidden = true)
    private Long idx;
    @ApiModelProperty(required = true,example = "김영호")
    private String name;
    @ApiModelProperty(required = true,example = "sofl")
    private String nickName;
    @ApiModelProperty(required = true,example = "Kyh1537@@@")
    private String pw;
    @ApiModelProperty(required = true,example = "010-5176-1023")
    private String phoneNumber;
    @ApiModelProperty(required = true,example = "kyh1537@naver.com")
    private String mail;
    @ApiModelProperty(example = "남")
    private String sex;
    @ApiModelProperty(hidden = true)
    private LocalDateTime regDate;
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateDate;
    @ApiModelProperty(hidden = true)
    private List<String> roles;
    @ApiModelProperty(hidden = true)
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

