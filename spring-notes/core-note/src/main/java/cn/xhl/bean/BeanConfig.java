package cn.xhl.bean;

import cn.xhl.async.AsyncService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-06 8:56
 */
public class BeanConfig {

	@Bean
	public AsyncService async() {
		return new AsyncService();
	}

	@Component
	static class Config{}
}
