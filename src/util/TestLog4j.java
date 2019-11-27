package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * TestLog4j类 测试Log4j
 * 
 * @author chenshaolei 2019年11月27日 上午11:40:08
 */
public class TestLog4j {

	public static Logger logger = LogManager.getLogger(Class.class);

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
//		try {
//			Log4j.LoggerINFO("infofooooo");
//			Log4j.LoggerERROR("?");
//		} catch (Exception e) {
//			System.err.println("cuo");
//			e.getStackTrace();
//		}
	}

	/**
	 * Test测试
	 */
	@Test
	public void testlog4j() {
		logger.error("error");
		logger.info("info");
		logger.debug("debug");
		logger.warn("warn");
	}
}
