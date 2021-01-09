package com.idus.homework.repository;


import com.idus.homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);

    Optional<User> findByIdx(long idx);

    @Query(value = "SELECT user.idx FROM user WHERE user.name = ?", nativeQuery = true)
    ArrayList<Long> findByName(String name);
}
