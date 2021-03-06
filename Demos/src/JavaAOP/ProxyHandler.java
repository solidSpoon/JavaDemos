package JavaAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(object, args);
        after();
        return null;
    }

    public ProxyHandler(Object object) {
        this.object = object;
    }

    private Object object;

    private void before() {
        System.out.println("proxy before methid");
    }

    private void after() {
        System.out.println("proxy after method");
    }
}
