package com.idus.homework.service;

import com.idus.homework.dto.UserDto;
import com.idus.homework.entity.User;
import com.idus.homework.repository.UserRepository;
import com.idus.homework.util.Crypto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public User getUserByIdx(long idx) {
        return userRepository.findByIdx(idx)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public User getUserByMail(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public ArrayList<Long> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    private void signUpValidateCheck(UserDto userDto) throws InputMismatchException {
        if (userDto.getName() == null || !userDto.getName().matches("^[가-힣a-zA-Z]*$")) {
            throw new InputMismatchException("이름은 한글, 영문 대소문자만 허용됩니다. input : " + userDto.getName());
        }

        if (userDto.getNickName() == null || !userDto.getNickName().matches("^[a-z]*$")) {
            throw new InputMismatchException("닉네임은 영문 소문자만 허용됩니다. input : " + userDto.getNickName());
        }

        String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{10,30}$";
        Matcher matcher = Pattern.compile(pwPattern).matcher(userDto.getPw());
        if (!matcher.matches()) {
            throw new InputMismatchException("비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야하며, 10글자 이상이여야합니다.");
        }

        if (userDto.getPhoneNumber() == null || !userDto.getPhoneNumber().matches("^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$")) {
            throw new InputMismatchException("올바르지 않은 전화번호 형식입니다. input : " + userDto.getPhoneNumber());
        }

        if (userDto.getMail() == null || !userDto.getMail().matches("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")) {
            throw new InputMismatchException("올바르지 않은 메일 형식입니다. input : " + userDto.getMail());
        }
    }

    public void signUpUser(UserDto userDto) throws InputMismatchException {
        this.signUpValidateCheck(userDto);
        userDto.setPw(Crypto.encrypt(userDto.getPw()));
        userDto.setRoles(Collections.singletonList("ROLE_ADMIN"));
        userRepository.save(userDto.toEntity());
    }

    public User login(String mail, String pw) {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new InputMismatchException("메일 정보가 올바르지 않습니다."));
        if (!user.getPassword().equals(Crypto.encrypt(pw))) {
            throw new InputMismatchException("비밀 번호 정보가 올바르지 않습니다.");
        }
        return user;
    }
}