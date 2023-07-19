package com.bridgelabz.userregistrationservice.service;

import com.bridgelabz.userregistrationservice.dto.BookDTO;
import com.bridgelabz.userregistrationservice.dto.ResponseDTO;
import com.bridgelabz.userregistrationservice.model.BookData;

import java.util.List;

public interface IBookService {
    ResponseDTO addBookToStore(BookDTO bookDTO);
    List<BookData> getAllBooks();
}
