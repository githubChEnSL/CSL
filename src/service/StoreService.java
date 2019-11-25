package service;

import java.util.List;


import entity.store;
import jdbc.DatabaseStore;

public class StoreService extends DatabaseStore{
	DatabaseStore dataStore;
	public StoreService() {
		dataStore=new DatabaseStore();
	}
	/**根据管理员编号获取所有商店的信息*/
	public List<store> GetAllStoreByRegulatorId(String regulatorId) {
		return dataStore.GetAllStoreByRegulatorId(regulatorId);
	}
	/**获取所有的商店信息*/
	public List<store> ListStore(){
		return dataStore.ListStore();
	}
	/**通过商店编号获取上商店信息*/
	public store getStoreForId(String storeId) {
		return dataStore.getStoreForId(storeId);
	}
	/**添加商店信息*/
	public boolean insertStore(store addobject) {
		return dataStore.insertStore(addobject);
	}
	/**删除用户信息*/
	public boolean deleteStore(String StoreId) {
		return dataStore.deleteStore(StoreId);
	}
	/**修改用户信息*/
	public boolean updateStore(store updatestore) {
		return dataStore.updateStore(updatestore);
	}
	/**关闭*/
	public void closeStoreService() {
		dataStore.CloseDatabase();
	}
	
}
