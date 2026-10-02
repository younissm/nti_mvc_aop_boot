package project.advice;

import org.jspecify.annotations.Nullable;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class LoggingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, @Nullable Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("BEFORE LOG: " + method.getName());
        for(var arg: args) {
            System.out.println("    ARG: " + arg);
        }
    }
}
