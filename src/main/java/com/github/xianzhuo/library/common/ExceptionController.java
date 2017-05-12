package com.github.xianzhuo.library.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception controller.
 * Deal with ServiceException
 *
 * @author shangjie
 */
@ControllerAdvice
public class ExceptionController {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ServiceException.class)
    public void handle(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOG.error("handle controller exception", e);
        try {
            boolean ajax = request.getHeader("X-Requested-With") != null && !request.getHeader("X-Requested-With").isEmpty() && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
            response.setStatus(500);
            if (ajax) {
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().println(JsonResult.message(e.getMessage()));
            } else {
                request.setAttribute("message", e.getCause());
                request.setAttribute("exception", ServiceException.class.getName());
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/common/error_500.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            LOG.error("handle exception occur error", ex);
        }
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOG.error("handle controller exception", e);
        try {
            boolean ajax = request.getHeader("X-Requested-With") != null && !request.getHeader("X-Requested-With").isEmpty() && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
            response.setStatus(500);
            if (ajax) {
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().println(JsonResult.message(e.getMessage()));
            } else {
                request.setAttribute("message", e.getCause());
                request.setAttribute("exception", Exception.class.getName());
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/common/error_500.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            LOG.error("handle exception occur error", ex);
        }
    }
}
