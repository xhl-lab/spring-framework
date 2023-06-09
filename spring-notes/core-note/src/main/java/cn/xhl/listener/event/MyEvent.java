package cn.xhl.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-06 10:05
 */
public class MyEvent extends ApplicationEvent {
	public MyEvent(Object source) {
		super(source);
	}
}
