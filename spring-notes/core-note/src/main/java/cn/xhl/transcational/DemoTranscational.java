package cn.xhl.transcational;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xhl
 * @Description
 * @CreateDate 2023-06-05 15:29
 */
@Component()
public class DemoTranscational {

	@Transactional(rollbackFor = Exception.class)
	public void doTrans() {
		System.out.println("doTrans");
	}
}
