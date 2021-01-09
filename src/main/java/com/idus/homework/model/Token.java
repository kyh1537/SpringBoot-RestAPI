package com.idus.homework.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Token {
    @ApiModelProperty(required = true,example = "1",position = 0)
    private long idx;
    @ApiModelProperty(required = true,example = "kyh1537@naver.com",position = 1)
    private String mail;
    @ApiModelProperty(required = true,example = "token value",position = 2)
    private String token;
}
