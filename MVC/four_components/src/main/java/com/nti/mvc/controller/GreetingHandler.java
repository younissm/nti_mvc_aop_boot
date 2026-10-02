package com.nti.mvc.controller;

import java.util.concurrent.atomic.AtomicInteger;

public class GreetingHandler {
    private AtomicInteger numberVisited = new AtomicInteger(0);
    public String greet(String firstName) {
        return "Hello, " + firstName + ". GreetHandler was called " + numberVisited.incrementAndGet() + " time(s)!";
    }
}
