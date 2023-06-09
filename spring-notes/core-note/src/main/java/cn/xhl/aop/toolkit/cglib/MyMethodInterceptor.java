package cn.xhl.aop.toolkit.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-01 11:23
 */
public class MyMethodInterceptor implements MethodInterceptor {

	private Object target;

	public MyMethodInterceptor(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("增强");
		return methodProxy.invoke(target, args);
	}
}
