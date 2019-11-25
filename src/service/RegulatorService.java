package service;

import java.util.List;


import entity.regulator;
import jdbc.DatabaseRegulator;
/**业务逻辑类*/
public class RegulatorService extends DatabaseRegulator{

	DatabaseRegulator DataRegulator;
	public RegulatorService() {
		DataRegulator=new DatabaseRegulator();
	}
	/**根据管理员角色编号获取管理员角色名称*/
	public String GetRegulatorRoleName(String regulatorRoleid) {
		return DataRegulator.GetRegulatorRoleName(regulatorRoleid);
	}
	/**获取所有的管理员信息*/
	public List<regulator> ListRegulator(){
		return DataRegulator.ListRegulator();
	}
	/**通过管理员编号获取管理员信息*/
	public regulator GetRegulatorForId(String regulatorId) {
		return DataRegulator.GetRegulatorForId(regulatorId);
	}
	/**添加管理员信息*/
	public boolean insertRegulator(regulator regulator) {
		return DataRegulator.insertRegulator(regulator);
	}
	/**删除管理员信息*/
	public boolean deleteRegulator(String regulatorId) {
		return DataRegulator.deleteRegulator(regulatorId);
	}
	/**修改管理员信息*/
	public boolean updateRegulator(regulator regulator) {
		return DataRegulator.updateRegulator(regulator);
	}
	/**关闭业务逻辑*/
	public void CloseRegulatorService() {
		DataRegulator.CloseDatabase();
	}
}
