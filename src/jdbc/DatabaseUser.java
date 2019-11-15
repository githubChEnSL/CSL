package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import entity.user;

public class DatabaseUser extends Database{

	/**根据用户员角色编号获取用户角色名称*/
	public String GetUserRoleName(String UserRoleid) {
		String RoleName = null;
		String sql="select * from user_role where roleid="+UserRoleid;
		try {
			//创建实例
			Statement GetUserRoleName=null;
			GetUserRoleName=createSta(GetUserRoleName);
			resuset=(Resultset) GetUserRoleName.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				RoleName=((ResultSet) resuset).getString("rolename");
			}
			//关闭实例
			 CloseStatement(GetUserRoleName);
		} catch (Exception e) {
			System.out.println("获取用户角色名称失败");
		}
		return RoleName;
	}
	/**获取所有的用户信息*/
	public List<user> ListUser(){
		List<user> listUser=new ArrayList<user>();
		String sql="select * from user";
		try {
			//创建实例
			Statement SelectStd=null;
			SelectStd=createSta(SelectStd);
			resuset= (Resultset) SelectStd.executeQuery(sql);
			 while(((ResultSet) resuset).next()){
		         String userid  = ((ResultSet) resuset).getString("userid");
		         String username = ((ResultSet) resuset).getString("username");
		         String password= ((ResultSet) resuset).getString("password");
		         String roleid = ((ResultSet) resuset).getString("roleid");
		         user object=new user();
		         object.setUserId(userid);
		         object.setUserName(username);
		         object.setPassword(password);
		         object.setRoleId(roleid);
		         listUser.add(object);
		      }
			//关闭实例
			 CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}
	/**通过用户编号获取用户信息*/
	public user getUserForId(String userid) {
		user getuser=new user();
		String sql="select * from user where userid="+userid;
		try {
			//创建实例
			Statement GetUser=null;
			GetUser=createSta(GetUser);
			resuset=(Resultset) GetUser.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				String ID=((ResultSet) resuset).getString("userid");
				String username=((ResultSet) resuset).getString("username");
				String password= ((ResultSet) resuset).getString("password");
				String roleid = ((ResultSet) resuset).getString("roleid");
				getuser.setUserId(ID);
				getuser.setUserName(username);
				getuser.setPassword(password);
				getuser.setRoleId(roleid);
			}
			System.out.println("获取用户信息成功");
			//关闭实例
			 CloseStatement(GetUser);
		} catch (Exception e) {
			System.out.println("获取用户信息失败");
		}
		return getuser;
	}
	/**添加用户信息*/
	public void insertUser(user addobject) {
		String ID=addobject.getUserId();
		String Name=addobject.getUserName();
		//默认密码为172056236
		String Password="172056236";
		String Roleid=addobject.getRoleId();
		
		String sql="insert into user value("+ID+",'"+Name+"','"+Password+"','"+Roleid+"')";
		try {
			//创建实例
			Statement adduser=null;
			adduser=createSta(adduser);
			adduser.execute(sql);
			System.out.println("添加用户信息成功");
			//关闭实例
			CloseStatement(adduser);
		} catch (Exception e) {
			System.out.println("添加用户信息失败");
		}
	}
	/**删除用户信息*/
	public void deleteUser(String userid) {
		String sql="delete from user where userid="+userid;
		try {
			//创建实例
			Statement deluser=null;
			deluser=createSta(deluser);
			deluser.execute(sql);
			//关闭实例
			CloseStatement(deluser);
			System.out.println("删除用户信息成功");
		} catch (Exception e) {
			System.out.println("删除用户信息失败");
		}
	}
	/**修改用户信息*/
	public void updateUser(user updateuser) {
		String ID=updateuser.getUserId();
		String Name=updateuser.getUserName();
		String Password=updateuser.getPassword();
		String Roleid=updateuser.getRoleId();
		
		String sql="update user set userid='"+ID+"',username='"+Name+"',password='"+Password+"',roleid='"+Roleid+"' where userid='"+ID+"'";
		try {
			//创建实例
			Statement Updateuser=null;
			Updateuser=createSta(Updateuser);
			Updateuser.execute(sql);
			System.out.println("修改用户信息成功");
			//关闭实例
			CloseStatement(Updateuser);
		} catch (Exception e) {
			System.out.println("修改用户信息失败");
		}	
	}
}
