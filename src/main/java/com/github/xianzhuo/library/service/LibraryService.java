package com.github.xianzhuo.library.service;

import com.github.xianzhuo.library.common.ServiceException;
import com.github.xianzhuo.library.model.Book;
import com.github.xianzhuo.library.model.Library;
import com.github.xianzhuo.library.persistence.BookMapper;
import com.github.xianzhuo.library.persistence.LibraryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by shangjie on 2017/5/10.
 */
@Service
@Transactional
public class LibraryService {
    private final static Logger LOG = LoggerFactory.getLogger(LibraryService.class);
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private LibraryMapper libraryMapper;

    public Book add(Book book) {
        if (book == null) {
            throw new ServiceException("");
        }
        bookMapper.insert(book);
        return book;
    }

    public Book get(String id) {
        return bookMapper.find(id);
    }

    public List<Book> getAll() {
        List result = bookMapper.all();
        return result == null ? Collections.EMPTY_LIST : result;
    }

    public void delete(String id) {
        if (get(id) == null) {
            return;
        }
        bookMapper.delete(id);
    }

    public Book update(Book book) {
        if (book == null) {
            throw new ServiceException("");
        }
        Book bookInDB = get(book.getId());
        if (bookInDB == null) {
            throw new ServiceException("");
        }
        if (book.getName() == null || book.getName().trim().isEmpty()) {
            book.setName(bookInDB.getName());
        }
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            book.setIsbn(bookInDB.getIsbn());
        }
        if (book.getPublisher() == null || book.getPublisher().trim().isEmpty()) {
            book.setPublisher(bookInDB.getPublisher());
        }
        if (book.getAuthors() == null || book.getAuthors().trim().isEmpty()) {
            book.setAuthors(bookInDB.getAuthors());
        }
        if (book.getCreatedTime() == null) {
            book.setCreatedTime(bookInDB.getCreatedTime());
        }
        book.setUpdatedTime(new Date());
        
        bookMapper.update(book);
        return book;
    }

    public Library addLibrary(Library library) {
        if (library == null) {
            throw new ServiceException("");
        }
        libraryMapper.insert(library);
        return library;
    }

    public Library getLibrary(String id) {
        return libraryMapper.find(id);
    }

    public List<Library> getAllLibraries() {
        List result = libraryMapper.all();
        return result == null ? Collections.EMPTY_LIST : result;
    }

    public void deleteLibrary(String id) {
        if (getLibrary(id) == null) {
            return;
        }
        libraryMapper.delete(id);
    }

    public Library updateLibrary(Library library) {
        if (library == null) {
            throw new ServiceException("");
        }
        Library libraryInDB = getLibrary(library.getId());
        if (libraryInDB == null) {
            throw new ServiceException("");
        }
        if (library.getName() == null || library.getName().trim().isEmpty()) {
            library.setName(libraryInDB.getName());
        }
        if (library.getParentId() == null || library.getParentId().trim().isEmpty()) {
            library.setParentId(libraryInDB.getParentId());
        }
        if (library.getCapacity() == null) {
            library.setCapacity(libraryInDB.getCapacity());
        }
        if (library.getSize() == null) {
            library.setSize(libraryInDB.getSize());
        }
        if (library.getWeight() == null) {
            library.setWeight(libraryInDB.getWeight());
        }
        if (library.getCreatedTime() == null) {
            library.setCreatedTime(libraryInDB.getCreatedTime());
        }

        library.setUpdatedTime(new Date());
        libraryMapper.update(library);
        return library;
    }
}
