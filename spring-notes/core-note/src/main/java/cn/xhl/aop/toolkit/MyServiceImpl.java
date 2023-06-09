package cn.xhl.aop.toolkit;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-01 10:40
 */
public class MyServiceImpl implements MyInterface, MyOtherInterface {
	@Override
	public void doSomeThing() {
		System.out.println("doSomeThing!!");
	}

	@Override
	public void doOtherSomeThing() {
		System.out.println("do otherSomeThing");
	}
}
