package cn.xhl.aop.toolkit.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-01 10:58
 */
public class MyInvocationHandler implements InvocationHandler {

	private final Object target;

	private MyAdvice myAdvice;

	public MyInvocationHandler(Object target) {
		this.target = target;
		myAdvice = new MyAdvice();
	}

	public Object getProxy(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		myAdvice.before();
		method.invoke(target, args);
		myAdvice.after();
		return null;
	}
}
