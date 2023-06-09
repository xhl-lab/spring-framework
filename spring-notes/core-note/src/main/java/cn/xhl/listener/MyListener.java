package cn.xhl.listener;

import cn.xhl.listener.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-06 9:39
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {

	@Override
	public void onApplicationEvent(MyEvent event) {
		System.out.println(event.getSource());
	}
}
