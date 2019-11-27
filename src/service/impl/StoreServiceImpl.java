package service.impl;

import java.util.List;

import dao.DaoStore;
import entity.store;
import service.StoreService;

/**
 * StoreServiceImpl类 实现StoreService接口
 * 
 * @author chenshaolei 2019年11月27日 下午12:18:42
 */
public class StoreServiceImpl implements StoreService {

	DaoStore dao;

	/**
	 * 初始化dao
	 */
	public StoreServiceImpl() {
		dao = new DaoStore();
	}

	@Override
	public List<String> ListStoresName() {
		return dao.ListStoresName();
	}

	@Override
	public List<store> GetAllStoreByRegulatorId(String regulatorId) {
		return dao.GetAllStoreByRegulatorId(regulatorId);
	}

	@Override
	public List<store> ListStore() {
		return dao.ListStore();
	}

	@Override
	public store getStoreForId(String storeId) {
		return dao.getStoreForId(storeId);
	}

	@Override
	public String getIdForName(String storeName) {
		return dao.getIdForName(storeName);
	}

	@Override
	public boolean insertStore(store addobject) {
		return dao.insertStore(addobject);
	}

	@Override
	public boolean deleteStore(String StoreId) {
		return dao.deleteStore(StoreId);
	}

	@Override
	public boolean updateStore(store updatestore) {
		return dao.updateStore(updatestore);
	}

	@Override
	public String GetMaxId() {
		return dao.GetMaxId();
	}

	@Override
	public void CloseStoreService() {
		dao.CloseDatabase();
	}

}
