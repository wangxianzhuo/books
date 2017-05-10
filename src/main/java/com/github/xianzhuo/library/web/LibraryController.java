package com.github.xianzhuo.library.web;

import com.github.xianzhuo.library.model.Book;
import com.github.xianzhuo.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;

/**
 * Created by shangjie on 2017/5/10.
 */
@Controller
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ServletRequest request, ServletResponse response) {
        return "dashboard";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String all(ServletRequest request) {
        request.setAttribute("books", libraryService.getAll());
        return "book/index";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String add(@RequestParam("isbn") String isbn,
                      @RequestParam("name") String name,
                      @RequestParam("publisher") String publisher,
                      @RequestParam("authors") String authors) {
        Book book = new Book(isbn, name, publisher, authors);
        book.setCreatedTime(new Date());
        libraryService.add(book);
        return "book/index";
    }
}
