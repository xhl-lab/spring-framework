package cn.xhl.listener;

import cn.xhl.listener.event.MyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-06 9:39
 */
@Configuration(proxyBeanMethods = false)
public class ListenerConfig {

	@EventListener
	/**
	 * 可以根据注解中classes属性类型或者参数类型来确定监听的事件。
	 * tips：
	 * 		两种方式，第一种优先级最高
	 * 		方法可以返回一个
	 * 		ApplicationEvent、CompletableFuture
	 *
	 */
	public CompletableFuture doContextRefershedListner(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		System.out.println("doContextRefershedListner");
		return CompletableFuture.supplyAsync(()-> {
			try {
				TimeUnit.SECONDS.sleep(10);
				return new MyEvent(applicationContext);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
