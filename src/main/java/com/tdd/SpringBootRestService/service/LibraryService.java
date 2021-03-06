package com.tdd.SpringBootRestService.service;

import com.tdd.SpringBootRestService.controller.Library;
import com.tdd.SpringBootRestService.repository.LibraryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibraryService {

  LibraryRepository libraryRepository;

  @Autowired
  public LibraryService(LibraryRepository libraryRepository) {
    this.libraryRepository = libraryRepository;
  }

  public String buildId(String isbn, int aisle) {
    if (isbn.startsWith("Z")) return "OLD_" + isbn + aisle;
    return isbn + aisle;
  }

  public String save(Library library) {
    Library saveBook = libraryRepository.save(library);
    return saveBook.getId();
  }

  public List<Library> findAll() {
    return libraryRepository.findAll();
  }

  public List<Library> findAllByAuthor(String authorName) {
    return libraryRepository.findAllByAuthor(authorName);
  }

  @Transactional(readOnly = true)
  public Optional<Library> findById(String bookId) {
    return libraryRepository.findById(bookId);
  }

  @Transactional(readOnly = true)
  public boolean checkBookAlreadyExist(String bookId) {
    Optional<Library> book = libraryRepository.findById(bookId);
    return book.isPresent();
  }

  public boolean delete(String bookId) {
    if (!libraryRepository.existsById(bookId)) return false;
    libraryRepository.deleteById(bookId);
    return true;
  }
}
