package project.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class TimingAdvice{
    Map<String, Integer> resultCache =new ConcurrentHashMap<>();

    @Around("execution(* *.*(..))")
    public @Nullable Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            System.out.println("LOG: calling " + pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            System.out.println("LOG: finished " +  pjp.getSignature().getName());
            System.out.println("LOG: Method took " + (System.currentTimeMillis() - start));
        }
    }
    @Around("@annotation(Cacheable)")
    public int aroundReturningCached(ProceedingJoinPoint pjp) throws Throwable {
        String key = pjp.getArgs()[1].toString();
        if (resultCache.containsKey(key)) {
            System.out.println(pjp.getSignature().getName() + " returned cached result " + resultCache.get(key));
            return resultCache.get(key);
        }
        Integer result = (Integer) pjp.proceed();
        System.out.println(pjp.getSignature().getName() + " returned result " + result);
        resultCache.put(key, result);
        return (Integer) result;
    }
}
