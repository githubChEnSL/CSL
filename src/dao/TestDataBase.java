package dao;

import org.junit.Test;

/**
 * TestDataBase类 测试DataBase
 * 
 * @author chenshaolei 2019年11月27日 上午11:53:42
 */
public class TestDataBase {

	/**
	 * 测试TestDatabase
	 */
	@Test
	public void TestDatabase() {
		try {
			Database database = new Database();
			database.CloseDatabase();
		} catch (Exception e) {
			System.err.println("连接数据库异常");
		}
	}
}
