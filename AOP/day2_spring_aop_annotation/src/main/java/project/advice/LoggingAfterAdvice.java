package project.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAfterAdvice {

    @AfterReturning(pointcut = "execution(* *.*(..))",
        returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println(jp.getSignature().getName() + " returned " + result);
    }

}
