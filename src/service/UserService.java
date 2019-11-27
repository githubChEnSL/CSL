package service;

import java.util.List;

import entity.user;

/**
 * UserService接口 为系统提供UserService接口
 * 
 * @author chenshaolei 2019年11月27日 下午12:04:53
 */
public interface UserService {

	/**
	 * 根据用户员角色编号获取用户角色名称
	 * 
	 * @param UserRoleid
	 * @return 返回用户角色名称
	 */
	public String GetUserRoleName(String UserRoleid);

	/**
	 * 根据用户员角色名称获取用户角色编号
	 * 
	 * @param UserRoleName
	 * @return 返回用户角色编号
	 */
	public String GetUserRoleId(String UserRoleName);

	/**
	 * 获取所有的用户信息
	 * 
	 * @return 返回所有的用户信息
	 */
	public List<user> ListUser();

	/**
	 * 通过用户编号获取用户信息
	 * 
	 * @param userid
	 * @return 返回用户信息
	 */
	public user getUserForId(String userid);

	/**
	 * 通过用户名称获取用户编号
	 * 
	 * @param username
	 * @return 返回用户编号
	 */
	public String getUserIdForName(String username);

	/**
	 * 添加用户信息
	 * 
	 * @param addobject
	 * @return 添加成功返回true,失败返回false
	 */
	public boolean insertUser(user addobject);

	/**
	 * 删除用户信息
	 * 
	 * @param userid
	 * @return 刪除成功返回true,失败返回false
	 */
	public boolean deleteUser(String userid);

	/**
	 * 修改用户信息
	 * 
	 * @param updateuser
	 * @return 修改成功返回true,失败返回false
	 */
	public boolean updateUser(user updateuser);

	/**
	 * 获取最大的客户编号
	 * 
	 * @return 返回最大的客户编号
	 */
	public String GetMaxId();

	/**
	 * 关闭UserService
	 */
	public void CloseService();
}
