package com.github.xianzhuo.library.service;

import com.github.xianzhuo.library.common.ServiceException;
import com.github.xianzhuo.library.model.Book;
import com.github.xianzhuo.library.persistence.BookMapper;
import com.github.xianzhuo.library.persistence.LibraryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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

    public void add(Book book) {
        if (book == null) {
            throw new ServiceException("");
        }
        bookMapper.insert(book);
    }

    public Book get(String id) {
        return bookMapper.find(id);
    }

    public List<Book> getAll() {
        List result = bookMapper.all();
        return result == null ? Collections.EMPTY_LIST : result;
    }
}
