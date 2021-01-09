package com.idus.homework.service;

import com.idus.homework.dto.UserDto;
import com.idus.homework.entity.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

public interface UserService {
    User getUserByIdx(long idx);

    User getUserByMail(String mail);

    ArrayList<Long> getUserByName(String name);

    void signUpValidateCheck(UserDto userDto) throws InputMismatchException;

    void signUpUser(UserDto userDto) throws InputMismatchException;

    User login(String mail, String pw);
}
