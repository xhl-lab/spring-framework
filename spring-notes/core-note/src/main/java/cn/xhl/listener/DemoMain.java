package cn.xhl.listener;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-06 10:41
 */
public class DemoMain {

	public static void main(String[] args) throws InterruptedException {
//		CompletableFuture<String> future = getFuture();
//		thenApply(future);
//		thenCombin(future);
//		applyTOEither(future);
//		thenCompose(future);

		IntStream.range(0, 10).boxed().forEach(i ->
				CompletableFuture
						.supplyAsync(DemoMain::readMysql)
						.thenApply(DemoMain::printData)
						.whenComplete((t, s) -> System.out.println("number: " + i + ", value: " + t + "Complete!"))
		);
/*
		List<CompletableFuture<Integer>> collect = IntStream.range(0, 10).boxed().map(i ->
						CompletableFuture
								.supplyAsync(DemoMain::readMysql)
								.thenApply(DemoMain::printData)
								.whenComplete((t, s) -> System.out.println("number: " + i + ", value: " + t + "Complete!"))
//						.join()
		).collect(Collectors.toList());
		CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();
		System.out.println("start....");

		System.out.println("end....");*/
//		TimeUnit.SECONDS.sleep(100);

		Thread.currentThread().join();
	}

	//模拟读取数据库（有耗时）
	private static int readMysql() {
		int value = ThreadLocalRandom.current().nextInt(20);
		try {
			System.out.println(Thread.currentThread().getName() + " -readMysql start.");
			TimeUnit.SECONDS.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " -readMysql end. value is:" + value);
		return value;
	}

	private static int printData(int data) {
		int value = ThreadLocalRandom.current().nextInt(20);
		try {

			System.out.println(Thread.currentThread().getName() + "-printData  start.");
			TimeUnit.SECONDS.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "- printData  end. " + data);
		return data;
	}

	private static CompletableFuture<String> getFuture() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("future start" + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(2);
				System.out.println("future end");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "hello";
		});
	}

	private static void thenCompose(CompletableFuture<String> future) {
		/**
		 * 需要等待上一个结果完成
		 */
		String compose = future.thenCompose(s -> CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("compose start" + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "HELLO" + s;
		})).join();
		System.out.println(compose);
	}

	private static void applyTOEither(CompletableFuture<String> future) {
		/**
		 * 后续函数参数为前面两个任务的随机一个。却决于谁先执行完
		 */
		String either = future.applyToEither(CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("either start" + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "HELLO";
		}), s -> s + " world").join();
		System.out.println(either);
	}

	private static void thenCombin(CompletableFuture<String> future) {
		/**
		 * thenCombine两个方法异步执行，但是后续操作需要等待两个都执行完成，并将两个返回值作为参数
		 */
		String combine = future.thenCombine(CompletableFuture.supplyAsync(() -> {
					try {
						System.out.println("combine start" + Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return " tom";
				}),
				(s1, s2) -> s1 + s2).join();
		System.out.println(combine);
	}

	private static void thenApply(CompletableFuture<String> future) {
		/**
		 * thenApply中的操作需要等待前面执行结束，并将返回值作为方法参数
		 */
		String join = future.thenApplyAsync(s -> {
			try {
				System.out.println("join" + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return s + " world";
		}).join();
		System.out.println(join);
	}
}
