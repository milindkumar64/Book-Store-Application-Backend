package com.bridgelabz.userregistrationservice.repository;

import com.bridgelabz.userregistrationservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

//Optional<UserData> findByEmailId(String emailId);

    @Query(value = "select * from book_store.user_data where email = :email",nativeQuery = true)
    UserData getUserIDByEmail(String email);
}
