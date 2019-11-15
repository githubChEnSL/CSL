package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class Database {
	private final String DatabaseURL="jdbc:mysql://localhost:3306/sso?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
	private final String DatabaseNAME="root";
	private final String DatabasePWD="123";
	protected Connection connection=null;//数据库连接的对象
	protected Resultset resuset=null; //数据库查询到的结果
	public Database() {
	        try {
	    		//创建驱动
				Class.forName("com.mysql.cj.jdbc.Driver");
				try {
					//连接数据库
					connection =  DriverManager.getConnection(DatabaseURL,DatabaseNAME,DatabasePWD);
					System.out.println("连接数据库-成功");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("连接数据库-失败");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("创建驱动失败");
			}
	}
	//关闭数据库
	public void CloseDatabase(){
		//关闭数据库
		try {
			if(connection!=null){
				connection.close();
				System.out.println("关闭数据库-成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("关闭数据库-失败");
		}
	}
	//关闭实例
	public void CloseStatement(Statement sta) {
		try {
			if(sta!=null) {
				sta.close();
				//关闭实例失败
			}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭实例-失败");
			}
		}
	//创建实例
	public Statement createSta(Statement sta) {
		try {
			sta=connection.createStatement();
			//创建实例成功
		} catch (Exception e) {
			System.out.println("创建实例-失败");
		}
		return sta;
	}
}
