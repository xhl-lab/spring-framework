package cn.xhl.aop;

import cn.xhl.aop.toolkit.*;
import cn.xhl.aop.toolkit.cglib.MyMethodInterceptor;
import cn.xhl.aop.toolkit.jdk.MyAdvice;
import cn.xhl.aop.toolkit.jdk.MyInvocationHandler;
import cn.xhl.aop.toolkit.MyOtherInterface;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-01 10:43
 */
public class AgentApplication {

	public static void main(String[] args) throws Exception {
		//保存字节码
		System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
//		executeJdkAgent();
//		executeJdkAgent2();
		executeCglibAgent();
	}

	private static void executeCglibAgent() throws Exception{
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(new MyMethodInterceptor(new MyServiceImpl()));
		enhancer.setSuperclass(MyServiceImpl.class);
		MyServiceImpl o = (MyServiceImpl) enhancer.create();
		Method method = o.getClass().getMethod("doOtherSomeThing");
		method.setAccessible(true);
		method.invoke(o);
	}

	private static void executeJdkAgent2() {
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new MyServiceImpl());
		MyOtherInterface proxy = (MyOtherInterface) myInvocationHandler.getProxy();
		proxy.doOtherSomeThing();
	}

	private static void executeJdkAgent() {
		/**
		 * JDK动态代理，只代理接口。
		 *
		 */
		MyServiceImpl myService = new MyServiceImpl();
		MyAdvice myAdvice = new MyAdvice();
		MyInterface o = (MyInterface) Proxy.newProxyInstance(myService.getClass().getClassLoader(),
				myService.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						myAdvice.before();
						Object invoke = method.invoke(myService, args);
						myAdvice.after();
						return invoke;
					}
				});
		o.doSomeThing();
	}
}
