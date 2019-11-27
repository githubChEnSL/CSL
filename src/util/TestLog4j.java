package util;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author chenshaolei <br>
 *         2019年11月26日 下午9:03:12
 * @Classname 
 * @Effect 
 */

/**
 * TestLog4j类 测试Log4j
 * 
 * @author chenshaolei 2019年11月27日 上午11:40:08
 */
public class TestLog4j {

	public static Logger logger = LogManager.getLogger(Test.class);

	/**
	 * 测试Logger
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.error("error");
		logger.info("info");
		logger.debug("debug");
		logger.warn("warn");
	}
}
