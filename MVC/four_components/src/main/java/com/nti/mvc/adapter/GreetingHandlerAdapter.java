package com.nti.mvc.adapter;

import com.nti.mvc.controller.GreetingHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

public class GreetingHandlerAdapter implements HandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return handler instanceof GreetingHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "Viewer";
        }

        ModelAndView modelAndView = new ModelAndView("greet");

        GreetingHandler greetingHandler = (GreetingHandler) handler;
        String message = greetingHandler.greet(name);
        modelAndView.getModel().put("message", message);

        return modelAndView;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }
}
