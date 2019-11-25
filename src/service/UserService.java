package service;

import java.util.List;

import entity.user;

public interface UserService {
	/** 根据用户员角色编号获取用户角色名称 */
	public String GetUserRoleName(String UserRoleid);

	/** 根据用户员角色名称获取用户角色编号 */
	public String GetUserRoleId(String UserRoleName);

	/** 获取所有的用户信息 */
	public List<user> ListUser();

	/** 通过用户编号获取用户信息 */
	public user getUserForId(String userid);

	/** 通过用户名称获取用户编号 */
	public String getUserIdForName(String username);

	/** 添加用户信息 */
	public boolean insertUser(user addobject);

	/** 删除用户信息 */
	public boolean deleteUser(String userid);

	/** 修改用户信息 */
	public boolean updateUser(user updateuser);

	/** 获取最大的客户编号 */
	public String GetMaxId();
	
	/**关闭daoUser*/
	public void CloseService();
}
