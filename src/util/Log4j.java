package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4j类
 * 
 * @author chenshaolei 2019年11月27日 下午2:48:17
 */
public class Log4j {

	/**
	 * 初始化Logger
	 */
	Log4j() {
		// 定义Log4j日志
	}

	/**
	 * 写入INFO级别的日志
	 * 
	 * @param info info级别
	 */
	public static void LoggerINFO(String info) {
		Logger logger = LogManager.getLogger(Class.class);
		logger.info(info);
	}

	/**
	 * 写入ERROE级别的日志
	 * 
	 * @param error error级别
	 */
	public static void LoggerERROR(String error) {
		Logger logger = LogManager.getLogger(Class.class);
		logger.error(error);
	}

	/**
	 * 写入WARN级别的日志
	 * 
	 * @param warn warn级别
	 */
	public static void LoggerWARN(String warn) {
		Logger logger = LogManager.getLogger(Class.class);
		logger.warn(warn);
	}
}
