package service;

import java.util.List;

import entity.regulator;

public interface RegulatorService {

	/** 获取所有的管理员角色名称 */
	public List<String> ListRegulatorRoleName();

	/** 根据管理员角色编号获取管理员角色名称 */
	public String GetRegulatorRoleName(String regulatorRoleid);

	/** 根据管理员角色名称获取管理员角色编号 */
	public String GetRegulatorRoleId(String regulatorRoleName);

	/** 获取所有的管理员信息 */
	public List<regulator> ListRegulator();

	/** 根据门店编号获取员工信息 */
	public List<regulator> listRegulatorByStoreId(String StoreId);

	/** 获取所有的普通员工的信息 */
	public List<regulator> listOrdinaryRegulators();

	/** 通过管理员编号获取管理员信息 */
	public regulator GetRegulatorForId(String regulatorId);

	/** 通过管理员名称获取管理员编号 */
	public String GetIdForName(String regulatorName);

	/** 添加管理员信息 */
	public boolean insertRegulator(regulator regulator);

	/** 删除管理员信息 */
	public boolean deleteRegulator(String regulatorId);

	/** 修改管理员信息 */
	public boolean updateRegulator(regulator regulator);

	/** 获取管理员编号的最大值 */
	public String GetMaxId();

	/** 关闭业务逻辑 */
	public void CloseRegulatorService();
}
