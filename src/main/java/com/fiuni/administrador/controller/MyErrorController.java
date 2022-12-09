package com.fiuni.administrador.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static org.apache.logging.log4j.LogManager.getLogger;

@Controller
public class MyErrorController implements ErrorController, error {

    public static final Logger l = getLogger(MyErrorController.class);






    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();

        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            l.error("Error 404");
            modelAndView.setViewName("404");




        }
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            l.info("Error 500");
            modelAndView.setViewName("error-500");


        }
        else {
            l.info("Error");
            modelAndView.setViewName("error");


        }


        return modelAndView;


    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}