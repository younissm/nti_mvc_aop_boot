package project.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingBeforeAdvice {

    @Before("execution(* *.*(..))")
    public void before(JoinPoint jp) {
        System.out.println("BEFORE LOG: " + jp.getSignature().getName());
        for(var arg: jp.getArgs()) {
            System.out.println(arg);
        }
    }
}
