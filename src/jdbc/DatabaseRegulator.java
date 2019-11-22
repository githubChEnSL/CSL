package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.mysql.cj.protocol.Resultset;

import entity.regulator;

public class DatabaseRegulator extends Database {

	/** 根据管理员角色编号获取管理员角色名称 */
	public String GetRegulatorRoleName(String regulatorRoleid) {
		String RoleName = null;
		String sql = "select * from regulator_role where regulator_role_id='" + regulatorRoleid+"'";
		try {
			// 创建实例
			Statement seleectReguRoleName = null;
			seleectReguRoleName = createSta(seleectReguRoleName);
			resuset = (Resultset) seleectReguRoleName.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				RoleName = ((ResultSet) resuset).getString("regulator_role_name");
			}
			// 关闭实例
			CloseStatement(seleectReguRoleName);
		} catch (Exception e) {
			System.out.println("获取管理员角色名称失败");
		}
		return RoleName;
	}
	/**根据管理员角色名称获取管理员角色编号*/
	public String GetRegulatorRoleId(String regulatorRoleName) {
		String RoleId = null;
		String sql = "select * from regulator_role where regulator_role_name='" + regulatorRoleName+"'";
		try {
			// 创建实例
			Statement seleectReguRoleName = null;
			seleectReguRoleName = createSta(seleectReguRoleName);
			resuset = (Resultset) seleectReguRoleName.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				RoleId = ((ResultSet) resuset).getString("regulator_role_id");
			}
			// 关闭实例
			CloseStatement(seleectReguRoleName);
		} catch (Exception e) {
			System.out.println("获取管理员角色名称失败");
		}
		return RoleId;
	}

	/** 获取所有的管理员信息 */
	public List<regulator> ListRegulator() {
		List<regulator> listregulators = new ArrayList<regulator>();
		String sql = "select * from regulator where regulator_role_id='2'";
		try {
			// 创建实例
			Statement SelectStd = null;
			SelectStd = createSta(SelectStd);
			resuset = (Resultset) SelectStd.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				String regulatorname = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				regulator regu = new regulator();
				regu.setRegulatorId(regulatorid);
				regu.setRegulatorName(regulatorname);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
				listregulators.add(regu);
			}
			// 关闭实例
			CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listregulators;
	}
	/**获取所有的普通员工的信息*/
	public List<regulator> listOrdinaryRegulators(){
		List<regulator> list=new ArrayList<regulator>();
		String sql = "select * from regulator where regulator_role_id='3'";
		try {
			// 创建实例
			Statement SelectStd = null;
			SelectStd = createSta(SelectStd);
			resuset = (Resultset) SelectStd.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				String regulatorname = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				regulator regu = new regulator();
				regu.setRegulatorId(regulatorid);
				regu.setRegulatorName(regulatorname);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
				list.add(regu);
			}
			// 关闭实例
			CloseStatement(SelectStd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/** 通过管理员编号获取管理员信息 */
	public regulator GetRegulatorForId(String regulatorId) {
		regulator regu = new regulator();
		String sql = "select * from regulator where regulatorid='" + regulatorId + "'";
		try {
			// 创建实例
			Statement GetRegulator = null;
			GetRegulator = createSta(GetRegulator);
			resuset = (Resultset) GetRegulator.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String ID = ((ResultSet) resuset).getString("regulatorid");
				String Name = ((ResultSet) resuset).getString("regulatorname");
				String password = ((ResultSet) resuset).getString("password");
				String regulatorRoleId = ((ResultSet) resuset).getString("regulator_role_id");
				regu.setRegulatorId(ID);
				regu.setRegulatorName(Name);
				regu.setPassword(password);
				regu.setRegulatorRoleId(regulatorRoleId);
			}
			System.out.println("获取管理员信息成功");
			// 关闭实例
			CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("获取管理员信息失败");
		}
		return regu;
	}

	/** 通过管理员名称获取管理员编号 */
	public String GetIdForName(String regulatorName) {
		String regulatorId = "";
		String sql = "select *  from regulator where regulatorname='" + regulatorName + "'";
		try {
			// 创建实例
			Statement GetRegulator = null;
			GetRegulator = createSta(GetRegulator);
			resuset = (Resultset) GetRegulator.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				regulatorId = ((ResultSet) resuset).getString("regulatorid");
			}
			System.out.println("获取管理员信息成功");
			// 关闭实例
			CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("获取管理员信息失败");
		}
		return regulatorId;
	}

	/** 添加管理员信息 */
	public boolean insertRegulator(regulator regulator) {
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
		String Name = regulator.getRegulatorName();
		// 默认密码为172056236
		String Password = "172056236";
		String Roleid = regulator.getRegulatorRoleId();
		String sql = "insert into regulator value('" + ID + "','" + Name + "','" + Password + "','" + Roleid + "')";
		try {
			// 创建实例
			Statement AddRegu = null;
			AddRegu = createSta(AddRegu);
			AddRegu.execute(sql);
			flag = true;
			System.out.println("添加管理员信息成功");
			// 关闭实例
			CloseStatement(AddRegu);
		} catch (Exception e) {
			System.out.println("添加管理员信息失败");
			flag = false;
		}
		return flag;
	}

	/** 删除管理员信息 */
	public boolean deleteRegulator(String regulatorId) {
		boolean flag = false;
		String sql = "delete from regulator where regulatorid=" + regulatorId;
		try {
			// 创建实例
			Statement delStatement = null;
			delStatement = createSta(delStatement);
			delStatement.execute(sql);
			// 关闭实例
			CloseStatement(delStatement);
			flag = true;
			System.out.println("删除管理员信息成功");
		} catch (Exception e) {
			flag = false;
			System.out.println("删除管理员信息失败");
		}
		return flag;
	}

	/** 修改管理员信息 */
	public boolean updateRegulator(regulator regulator) {
		boolean flag = false;
		String ID = regulator.getRegulatorId();
		String Name = regulator.getRegulatorName();
		String Password = regulator.getPassword();
		String Roleid = regulator.getRegulatorRoleId();

		String sql = "update regulator set regulatorid='" + ID + "',regulatorname='" + Name + "',password='" + Password
				+ "',regulator_role_id='" + Roleid + "' where regulatorid='" + ID + "'";
		try {
			// 创建实例
			Statement UpdateRegu = null;
			UpdateRegu = createSta(UpdateRegu);
			UpdateRegu.execute(sql);
			flag = true;
			System.out.println("修改管理员信息成功");
			// 关闭实例
			CloseStatement(UpdateRegu);
		} catch (Exception e) {
			flag = false;
			System.out.println("修改管理员信息失败");
		}
		return flag;
	}

	/** 获取管理员编号的最大值 */
	public String GetMaxId() {
		List<Integer> listId = new ArrayList<Integer>();
		String sql = "select * from regulator";
		try {
			// 创建实例
			Statement GetRegulator = null;
			GetRegulator = createSta(GetRegulator);
			resuset = (Resultset) GetRegulator.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				listId.add(Integer.parseInt(((ResultSet) resuset).getString("regulatorid")));
			}
			System.out.println("获取信息成功");
			// 关闭实例
			CloseStatement(GetRegulator);
		} catch (Exception e) {
			System.out.println("获取信息失败");
		}
		return Collections.max(listId).toString();
	}
}
