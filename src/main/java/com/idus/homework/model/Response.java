package com.idus.homework.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response {
    @ApiModelProperty(required = true,example = "success",position = 0)
    private String response;
    @ApiModelProperty(required = true,example = "성공", position = 1)
    private String message;
    @ApiModelProperty(required = true,example = "{}", position = 2)
    private Object data;
}
