package project.advice;

import org.jspecify.annotations.Nullable;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class LoggingAfterAdvice implements AfterReturningAdvice {


    @Override
    public void afterReturning(@Nullable Object returnValue, Method method, @Nullable Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("AFTER LOG: " + method.getName() + " returned " + (int) returnValue);
    }
}
