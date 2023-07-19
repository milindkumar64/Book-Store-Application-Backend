package com.bridgelabz.userregistrationservice.service;

import com.bridgelabz.userregistrationservice.dto.BookDTO;
import com.bridgelabz.userregistrationservice.dto.ResponseDTO;
import com.bridgelabz.userregistrationservice.model.BookData;
import com.bridgelabz.userregistrationservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements IBookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public ResponseDTO addBookToStore(BookDTO bookDTO) {
        List<BookData> book = bookRepository.findAll().stream().filter(data-> data.getBookName().equals(bookDTO.getBookName())).collect(Collectors.toList());
        if(book.isEmpty()){
            BookData bookData = new BookData(bookDTO);
            bookRepository.save(bookData);
            return new ResponseDTO("Book Added Successfully..", bookData);
        }
        return new ResponseDTO("Book Already Present..", book);
    }

    @Override
    public List<BookData> getAllBooks() {
        return bookRepository.findAll();
    }

}
