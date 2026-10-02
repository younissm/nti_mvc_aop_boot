package project.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingThrowsAdvice implements ThrowsAdvice {
    @AfterThrowing("execution(* *.*(..))")
    public void afterThrowing(JoinPoint jp,
                              Exception ex) {
        System.out.println("ERROR: " + ex.getMessage());
    }
}
