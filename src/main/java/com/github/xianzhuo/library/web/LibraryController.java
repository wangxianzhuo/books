package com.github.xianzhuo.library.web;

import com.github.xianzhuo.library.common.GsonHelper;
import com.github.xianzhuo.library.common.JsonResult;
import com.github.xianzhuo.library.common.ServiceException;
import com.github.xianzhuo.library.model.Book;
import com.github.xianzhuo.library.model.Library;
import com.github.xianzhuo.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<String> add(@RequestParam("isbn") String isbn,
                                      @RequestParam("name") String name,
                                      @RequestParam("publisher") String publisher,
                                      @RequestParam("authors") String authors) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(JsonResult.message("isbn不能为空").toString());
        }
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(JsonResult.message("书名不能为空").toString());
        }
        if (publisher == null || publisher.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(JsonResult.message("出版社不能为空").toString());
        }
        if (authors == null || authors.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(JsonResult.message("作者不能为空").toString());
        }

        Book book = new Book(isbn, name, publisher, authors);
        book.setCreatedTime(new Date());
        return ResponseEntity.ok(GsonHelper.DEFAULT_GSON.toJson(libraryService.add(book)));
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        libraryService.delete(id);
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable("id") String id,
                                         @RequestParam("isbn") String isbn,
                                         @RequestParam("name") String name,
                                         @RequestParam("publisher") String publisher,
                                         @RequestParam("authors") String authors) {
        Book book = new Book(isbn, name, publisher, authors);
        book.setId(id);
        return ResponseEntity.ok(GsonHelper.DEFAULT_GSON.toJson(libraryService.update(book)));
    }

    @RequestMapping(value = "/libraries", method = RequestMethod.GET)
    public String allLibraries(ServletRequest request) {
        request.setAttribute("libraries", libraryService.getAllLibraries());
        return "library/index";
    }

    @RequestMapping(value = "/library", method = RequestMethod.POST)
    public ResponseEntity<String> addLibrary(@RequestParam("name") String name,
                                             @RequestParam("parentId") String parentId,
                                             @RequestParam("weight") Integer weight,
                                             @RequestParam("capacity") Long capacity,
                                             @RequestParam("size") Long size) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(JsonResult.message("名称不能为空").toString());
        }
        if (weight == null) {
            weight = 0;
        }
        if (capacity == null) {
            capacity = 0L;
        }
        if (size == null) {
            size = 0L;
        }
        Library library = new Library(name, parentId, weight, capacity, size);
        return ResponseEntity.ok(GsonHelper.DEFAULT_GSON.toJson(libraryService.addLibrary(library)));
    }

    @RequestMapping(value = "/library/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLibrary(@PathVariable("id") String id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/library/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateLibrary(@PathVariable("id") String id,
                                                @RequestParam("name") String name,
                                                @RequestParam("parentId") String parentId,
                                                @RequestParam("weight") Integer weight,
                                                @RequestParam("capacity") Long capacity,
                                                @RequestParam("size") Long size) {
        Library library = new Library(name, parentId, weight, capacity, size);
        library.setId(id);
        return ResponseEntity.ok(GsonHelper.DEFAULT_GSON.toJson(libraryService.updateLibrary(library)));
    }
}
