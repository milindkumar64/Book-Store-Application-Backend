package com.bridgelabz.userregistrationservice.repository;

import com.bridgelabz.userregistrationservice.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookData,Long> {

    @Query(value = "select * from book_store.user_data where name = :name",nativeQuery = true)
    BookData getBookByName(String name);
}
