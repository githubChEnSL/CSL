package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.protocol.Resultset;

/**
 * Database类 连接数据库,创建实例
 * 
 * @author chenshaolei 2019年11月27日 上午11:26:21
 */
public class Database {
	private final String DatabaseURL = "jdbc:mysql://localhost:3306/sso?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";// 数据库地址
	private final String DatabaseNAME = "root";// 数据库账号
	private final String DatabasePWD = "123";// 数据库密码
	protected Connection connection = null;// 数据库连接的对象
	protected Resultset resuset = null; // 数据库查询到的结果

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	/**
	 * 构造函数（连接数据库）
	 */
	public Database() {
		try {
			// 创建驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				// 连接数据库
				connection = DriverManager.getConnection(DatabaseURL, DatabaseNAME, DatabasePWD);
				//logger.info("连接数据库成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("连接数据库失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("创建驱动失败");
		}
	}

	/**
	 * 关闭数据库
	 */
	public void CloseDatabase() {
		try {
			if (connection != null) {
				connection.close();
				//logger.info("关闭数据库-成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("关闭数据库-失败");
		}
	}

	/**
	 * 创建实例
	 * 
	 * @param sta
	 * @return 返回一个实例
	 */
	public Statement createSta(Statement sta) {
		try {
			sta = connection.createStatement();
			// 创建实例成功
		} catch (Exception e) {
			// System.out.println("创建实例-失败");
			logger.error("创建实例-失败");
		}
		return sta;
	}

	/**
	 * 关闭实例
	 * 
	 * @param sta
	 */
	public void CloseStatement(Statement sta) {
		try {
			if (sta != null) {
				sta.close();
				// 关闭实例成功
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("关闭实例失败");
			//System.out.println("关闭实例-失败");
		}
	}
}
