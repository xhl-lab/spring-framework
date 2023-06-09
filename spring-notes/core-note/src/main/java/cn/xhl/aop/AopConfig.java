package cn.xhl.aop;

import cn.xhl.aop.toolkit.MyInterface;
import cn.xhl.aop.toolkit.MyServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-09 9:01
 */
@Configuration
public class AopConfig {

	@Bean
	public MyInterface myInterface(BeanFactory beanFactory) {
		/**
		 * 优先级： targetName -> targetClass = target
		 *
		 * {@link ProxyFactoryBean#freshTargetSource()}
		 *
		 * Tips：1、如果设置了targetName，则在Spring容器中，需要存在此名称的bean
		 * 		2、如果设置了targetName，则设置targetClass和target都会失效
		 * 	   	3、targetClass和target会覆盖
		 *
		 */

		MyInterface myService = new MyServiceImpl();
		ProxyFactoryBean proxy = new ProxyFactoryBean();
//		proxy.setTarget(myService);
//		proxy.setTargetClass(MyInterface.class);
		proxy.setTargetName("myServiceImpl");
		proxy.setBeanFactory(beanFactory);
		proxy.setProxyTargetClass(false);
		proxy.addAdvice((MethodInterceptor) invocation -> {
			String name = invocation.getMethod().getName();
			System.out.println("方法：" + name + "增强");
			return invocation.proceed();
		});
		return (MyInterface) proxy.getObject();
	}

	@Bean
	public MyServiceImpl myServiceImpl() {
		return new MyServiceImpl();
	}
}
