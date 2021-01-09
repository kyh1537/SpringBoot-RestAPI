package com.idus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Token {
    private long idx;
    private String mail;
    private String token;
}
