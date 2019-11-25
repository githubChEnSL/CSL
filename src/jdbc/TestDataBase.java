package jdbc;

import org.junit.Test;

public class TestDataBase {
	/****************************************************** Database */
	@Test
	public void TestDatabase() {
		try {
			Database database=new Database();
			database.CloseDatabase();
		} catch (Exception e) {
			System.err.println("连接数据库异常");
		}
	}
}
