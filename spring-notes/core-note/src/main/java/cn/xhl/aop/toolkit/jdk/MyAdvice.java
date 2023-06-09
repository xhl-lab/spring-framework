package cn.xhl.aop.toolkit.jdk;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-01 10:41
 */
public class MyAdvice {

	public void before() {
		System.out.println("before execute!!");
	}

	public void after() {
		System.out.println("after execute!!");
	}
}
