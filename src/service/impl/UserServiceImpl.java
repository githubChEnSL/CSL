package service.impl;

import java.util.List;

import dao.DaoUser;
import entity.user;
import service.UserService;

/**
 * UserServiceImpl类 实现UserService接口
 * 
 * @author chenshaolei 2019年11月27日 下午12:17:53
 */
public class UserServiceImpl implements UserService {

	DaoUser dao;

	/**
	 * 初始化dao
	 */
	public UserServiceImpl() {
		dao = new DaoUser();
	}

	@Override
	public String GetUserRoleName(String UserRoleid) {
		return dao.GetUserRoleName(UserRoleid);
	}

	@Override
	public String GetUserRoleId(String UserRoleName) {
		return dao.GetUserRoleId(UserRoleName);
	}

	@Override
	public List<user> ListUser() {
		return dao.ListUser();
	}

	@Override
	public user getUserForId(String userid) {
		return dao.getUserForId(userid);
	}

	@Override
	public String getUserIdForName(String username) {
		return dao.getUserIdForName(username);
	}

	@Override
	public boolean insertUser(user addobject) {
		return dao.insertUser(addobject);
	}

	@Override
	public boolean deleteUser(String userid) {
		return dao.deleteUser(userid);
	}

	@Override
	public boolean updateUser(user updateuser) {
		return dao.updateUser(updateuser);
	}

	@Override
	public String GetMaxId() {
		return dao.GetMaxId();
	}

	@Override
	public void CloseService() {
		dao.CloseDatabase();
	}

}
