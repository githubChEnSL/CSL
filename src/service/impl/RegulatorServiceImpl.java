package service.impl;

import java.util.List;

import dao.DaoRegulator;
import entity.regulator;
import service.RegulatorService;

public class RegulatorServiceImpl implements RegulatorService {

	DaoRegulator dao;

	public RegulatorServiceImpl() {
		dao = new DaoRegulator();
	}

	@Override
	public List<String> ListRegulatorRoleName() {
		return dao.ListRegulatorRoleName();
	}

	@Override
	public String GetRegulatorRoleName(String regulatorRoleid) {
		return dao.GetRegulatorRoleName(regulatorRoleid);
	}

	@Override
	public String GetRegulatorRoleId(String regulatorRoleName) {
		return dao.GetRegulatorRoleId(regulatorRoleName);
	}

	@Override
	public List<regulator> ListRegulator() {
		return dao.ListRegulator();
	}

	@Override
	public List<regulator> listRegulatorByStoreId(String StoreId) {
		return dao.listRegulatorByStoreId(StoreId);
	}

	@Override
	public List<regulator> listOrdinaryRegulators() {
		return dao.listOrdinaryRegulators();
	}

	@Override
	public regulator GetRegulatorForId(String regulatorId) {
		return dao.GetRegulatorForId(regulatorId);
	}

	@Override
	public String GetIdForName(String regulatorName) {
		return dao.GetIdForName(regulatorName);
	}

	@Override
	public boolean insertRegulator(regulator regulator) {
		return dao.insertRegulator(regulator);
	}

	@Override
	public boolean deleteRegulator(String regulatorId) {
		return dao.deleteRegulator(regulatorId);
	}

	@Override
	public boolean updateRegulator(regulator regulator) {
		return dao.updateRegulator(regulator);
	}

	@Override
	public String GetMaxId() {
		return dao.GetMaxId();
	}

	@Override
	public void CloseRegulatorService() {
		dao.CloseDatabase();
	}
}
