package com.librarybe.librarybe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.librarybe.librarybe.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select c from User c where c.isDeleted = false order by c.name")
    List<User> getAllUser();

    @Query(value = "select c from User c where c.id = :id and c.isDeleted = false ")
    User getUserById(Long id);
}