package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestDatabase {

	@Test
	public  void test() {
        try {
        	//驱动
			Class.forName("com.mysql.jdbc.Driver");
				try {
					//连接数据库
					Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/sso?useUnicode=true&characterEncoding=utf-8","root","123");
					//创建实例
					Statement createStatement = connection.createStatement();
					createStatement.close();
					System.out.println("创建成功");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
	}
}
