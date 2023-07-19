package com.bridgelabz.userregistrationservice.controller;

import com.bridgelabz.userregistrationservice.dto.BookDTO;
import com.bridgelabz.userregistrationservice.dto.ResponseDTO;
import com.bridgelabz.userregistrationservice.model.BookData;
import com.bridgelabz.userregistrationservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService iBookBusinessLogics;

    @PostMapping("/addBook")
    public ResponseDTO addBookToStore(@RequestBody BookDTO bookDTO){
        return iBookBusinessLogics.addBookToStore(bookDTO);
    }

    @GetMapping(value = {"/allBook"})
    public List<BookData> getAllBooks(){
        return iBookBusinessLogics.getAllBooks();
    }



}
