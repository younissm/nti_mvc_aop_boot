package project.advice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

public class TimingAdvice implements MethodInterceptor {
    @Override
    public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            System.out.println("AROUND LOG: calling " + invocation.getMethod().getName());
            return invocation.proceed();
        } finally {
            System.out.println("AROUND LOG: finished " +  invocation.getMethod().getName());
            System.out.println("AROUND LOG: Method took " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
