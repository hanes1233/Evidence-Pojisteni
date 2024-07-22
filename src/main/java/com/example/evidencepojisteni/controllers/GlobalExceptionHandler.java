package com.example.evidencepojisteni.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GlobalExceptionHandler implements ErrorController {

    // Global error handler
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status == null) {
            return "/errors/error";
        }
        int statusCode = Integer.parseInt(status.toString());
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return "/errors/error-404";
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "/errors/error-500";
        }else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
            return "/errors/error-400";
        }
        return "/errors/error";
    }
}