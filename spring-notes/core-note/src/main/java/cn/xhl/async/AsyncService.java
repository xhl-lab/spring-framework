package cn.xhl.async;

import cn.xhl.async.inter.AInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-05-30 8:51
 */
@Configuration(proxyBeanMethods = false)
@EnableAsync()
public class AsyncService implements AInterface {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public Executor a() {
		Executor executor = asyncServiceExcutor();
		return executor;
	}
	@Bean("asyncServiceExcutor")
	public Executor asyncServiceExcutor() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}


	public String aysnc() {
		applicationContext.getBean(AsyncService.class).doAysnc1();
		applicationContext.getBean(AsyncService.class).doAysnc2();
		return "ok";
	}

	@Async("asyncServiceExcutor")
	public void doAysnc1() {
		System.out.println("执行异步方法doAysnc1:::" + Thread.currentThread().getName());
	}

	@Async("asyncServiceExcutor")
	public void doAysnc2() {
		System.out.println("执行异步方法doAysnc2:::" + Thread.currentThread().getName());
	}

}
