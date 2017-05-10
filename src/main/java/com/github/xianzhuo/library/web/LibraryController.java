package com.github.xianzhuo.library.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by shangjie on 2017/5/10.
 */
@Controller
public class LibraryController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ServletRequest request, ServletResponse response) {
        return "index";
    }
}
