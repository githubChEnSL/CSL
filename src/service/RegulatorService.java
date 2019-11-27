package service;

import java.util.List;

import entity.regulator;

/**
 * RegulatorService接口 为系统提供RegulatorService接口
 * 
 * @author chenshaolei 2019年11月27日 上午11:54:50
 */
public interface RegulatorService {

	/**
	 * 获取所有的管理员角色名称
	 * 
	 * @return 返回所有的管理员角色名称
	 */
	public List<String> ListRegulatorRoleName();

	/**
	 * 根据管理员角色编号获取管理员角色名称
	 * 
	 * @param regulatorRoleid
	 * @return 返回管理员角色名称
	 */
	public String GetRegulatorRoleName(String regulatorRoleid);

	/**
	 * 根据管理员角色名称获取管理员角色编号
	 * 
	 * @param regulatorRoleName
	 * @return 返回管理员角色编号
	 */
	public String GetRegulatorRoleId(String regulatorRoleName);

	/**
	 * 获取所有的管理员信息
	 * 
	 * @return 返回所有的管理员信息
	 */
	public List<regulator> ListRegulator();

	/**
	 * 根据门店编号获取员工信息
	 * 
	 * @param StoreId
	 * @return 返回员工信息
	 */
	public List<regulator> listRegulatorByStoreId(String StoreId);

	/**
	 * 获取所有的普通员工的信息
	 * 
	 * @return 返回普通员工的信息
	 */
	public List<regulator> listOrdinaryRegulators();

	/**
	 * 通过管理员编号获取管理员信息
	 * 
	 * @param regulatorId
	 * @return 返回管理员信息
	 */
	public regulator GetRegulatorForId(String regulatorId);

	/**
	 * 通过管理员名称获取管理员编号
	 * 
	 * @param regulatorName
	 * @return 返回管理员编号
	 */
	public String GetIdForName(String regulatorName);

	/**
	 * 添加管理员信息
	 * 
	 * @param regulator
	 * @return 添加成功返回true,失败返回false
	 */
	public boolean insertRegulator(regulator regulator);

	/**
	 * 删除管理员信息
	 * 
	 * @param regulatorId
	 * @return 删除成功返回true,失败返回false
	 */
	public boolean deleteRegulator(String regulatorId);

	/**
	 * 修改管理员信息
	 * 
	 * @param regulator
	 * @return 修改成功返回true,失败返回false
	 */
	public boolean updateRegulator(regulator regulator);

	/**
	 * 获取管理员编号的最大值
	 * 
	 * @return 返回管理员的最大编号
	 */
	public String GetMaxId();

	/**
	 * 关闭RegulatorService
	 */
	public void CloseRegulatorService();
}
