package cn.xhl;

import cn.xhl.aop.toolkit.MyInterface;
import cn.xhl.async.AsyncService;
import cn.xhl.env.MyEnvConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-05-30 8:51
 */
@Configuration()
@ComponentScan
public class CoreApplication {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoreApplication.class);
		MyInterface bean = context.getBean(MyInterface.class);
		bean.doSomeThing();
	}
}
