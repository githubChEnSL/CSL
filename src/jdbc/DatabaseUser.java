package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import entity.user;

public class DatabaseUser extends Database{

	public DatabaseUser(){
		super();
	}
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
	/**根据用户员角色名称获取用户角色编号*/
	public String GetUserRoleId(String UserRoleName) {
		String RoleId = null;
		String sql="select * from user_role where rolename='"+UserRoleName+"'";
		try {
			//创建实例
			Statement GetUserRoleName=null;
			GetUserRoleName=createSta(GetUserRoleName);
			resuset=(Resultset) GetUserRoleName.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				RoleId=((ResultSet) resuset).getString("roleid");
			}
			//关闭实例
			 CloseStatement(GetUserRoleName);
		} catch (Exception e) {
			System.out.println("获取用户角色编号失败");
		}
		return RoleId;
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
	/**通过用户名称获取用户编号*/
	public String getUserIdForName(String username) {
		String getId="";
		String sql="select * from user where username='"+username+"'";
		try {
			//创建实例
			Statement GetUser=null;
			GetUser=createSta(GetUser);
			resuset=(Resultset) GetUser.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				getId=((ResultSet) resuset).getString("userid");
			}
			System.out.println("获取用户信息成功");
			//关闭实例
			 CloseStatement(GetUser);
		} catch (Exception e) {
			System.out.println("获取用户信息失败");
		}
		return getId;
	}
	/**添加用户信息*/
	public boolean insertUser(user addobject) {
		boolean flag = false;
		String Number = "";
		// 判断表中是否存在最大值（表是否为空）
		try {
			if ("".equals(this.GetMaxId())) {
				Number = "0";
			} else {
				Number = this.GetMaxId();
			}
		} catch (Exception e) {
			Number = "0";
		}
		String ID = String.valueOf(Integer.parseInt(Number)+1);
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
			flag = true;
			System.out.println("添加用户信息成功");
			//关闭实例
			CloseStatement(adduser);
		} catch (Exception e) {
			System.err.println("添加用户信息失败");
			flag = false;
		}
		return flag;
	}
	/**删除用户信息*/
	public boolean deleteUser(String userid) {
		boolean flag = false;
		String sql="delete from user where userid="+userid;
		try {
			//创建实例
			Statement deluser=null;
			deluser=createSta(deluser);
			deluser.execute(sql);
			System.out.println("删除用户信息成功");
			//关闭实例
			CloseStatement(deluser);
			flag = true;
		} catch (Exception e) {
			System.err.println("删除用户信息失败");
			flag = false;
		}
		return flag;
	}
	/**修改用户信息*/
	public boolean updateUser(user updateuser) {
		boolean flag = false;
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
			flag = true;
			//关闭实例
			CloseStatement(Updateuser);
		} catch (Exception e) {
			flag = false;
			System.err.println("修改用户信息失败");
		}
		return flag;
	}
	/** 获取最大的客户编号 */
	public String GetMaxId() {
		List<Integer> listId = new ArrayList<Integer>();
		String sql = "select * from user";
		try {
			// 创建实例
			Statement GetStore = null;
			GetStore = createSta(GetStore);
			resuset = (Resultset) GetStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				listId.add(Integer.parseInt(((ResultSet) resuset).getString("userid")));
			}
			System.out.println("获取信息成功");
			// 关闭实例
			CloseStatement(GetStore);
		} catch (Exception e) {
			System.out.println("获取信息失败");
		}
		return Collections.max(listId).toString();
	}
}
