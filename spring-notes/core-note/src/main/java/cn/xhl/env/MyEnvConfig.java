package cn.xhl.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-06 17:20
 */

@Configuration(proxyBeanMethods = false)
public class MyEnvConfig {

	@Autowired
	private Environment environment;

	@Value("${person.name}")
	private String name;

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
