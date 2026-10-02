import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingHandler implements InvocationHandler {

    Object real;

    public LoggingHandler(Object real) {
        this.real = real;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method name: " + method.getName());
        System.out.println("Method args: " + args);
		
        long start = System.currentTimeMillis();
        System.out.println("START: " + method.getName());
		
        Object result = method.invoke(real, args);
		
        System.out.println("END: took " + (System.currentTimeMillis()- start) + "ms");
		
        return result;
    }
}
