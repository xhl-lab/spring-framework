package com.xhl;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-05-29 15:13
 */
@Configuration
public class MvcApplication {
	public static void main(String[] args) throws LifecycleException {
		startTomcat();
	}

	private static void startTomcat() throws LifecycleException {
//		Tomcat tomcat = new Tomcat();
//
//		Connector connector = new Connector();
//		connector.setPort(8080);
//		connector.setURIEncoding("UTF-8");
//		tomcat.getService().addConnector(connector);
//
//
//		tomcat.addContext("/", null);
//
//		tomcat.start();
//		tomcat.getServer().await();

		Tomcat tomcatServer = new Tomcat();

		// 设置Tomcat端口
		tomcatServer.setPort(8080);

		Connector connector = new Connector(Http11NioProtocol.class.getName());
		connector.setPort(8080);
		tomcatServer.getService()
				.addConnector(connector);
		tomcatServer.setConnector(connector);

		// 读取项目路径，加载项目资源
		StandardContext ctx = (StandardContext) tomcatServer.addWebapp(
				"/mvc", new File("source-note-springmvc/src/main/webapp").getAbsolutePath());

		// 不重新部署加载资源
		ctx.setReloadable(false);

		// 创建 WebRoot
		WebResourceRoot resources = new StandardRoot(ctx);

		// 指定编译后的 class 文件位置
		File additionalWebInfClasses = new File("source-note-springmvc/out/produ?ction/classes");

		// 添加web资源
		resources.addPreResources(new DirResourceSet(resources, "/", additionalWebInfClasses.getAbsolutePath(), "/"));

		// 启动内嵌的Tomcat
		tomcatServer.start();

		Thread thread = new Thread(() -> {
			// 堵塞，不退出程序
			tomcatServer.getServer()
					.await();
		});
		thread.setDaemon(false);
		thread.start();
	}
}
