package service;

import java.util.List;

import entity.store;

public interface StoreService {

	/** 获取所有的门店名称 */
	public List<String> ListStoresName();

	/** 根据管理员编号获取所有商店的信息 */
	public List<store> GetAllStoreByRegulatorId(String regulatorId);

	/** 获取所有的商店信息 */
	public List<store> ListStore();

	/** 通过商店编号获取商店信息 */
	public store getStoreForId(String storeId);

	/** 通过门店名称获取商店编号 */
	public String getIdForName(String storeName);

	/** 添加商店信息 */
	public boolean insertStore(store addobject);

	/** 删除商店信息 */
	public boolean deleteStore(String StoreId);

	/** 修改用户信息 */
	public boolean updateStore(store updatestore);

	/** 获取最大的商店编号 */
	public String GetMaxId();

	/** 关闭StoreService */
	public void CloseStoreService();
}
