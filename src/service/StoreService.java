package service;

import java.util.List;

import entity.store;

/**
 * StoreService接口 为系统提供StoreService接口
 * 
 * @author chenshaolei 2019年11月27日 下午12:00:49
 */
public interface StoreService {

	/**
	 * 获取所有的门店名称
	 * 
	 * @return 返回门店名称
	 */
	public List<String> ListStoresName();

	/**
	 * 根据管理员编号获取所有商店的信息
	 * 
	 * @param regulatorId
	 * @return 返回按条件查询到的所有商店信息
	 */
	public List<store> GetAllStoreByRegulatorId(String regulatorId);

	/**
	 * 获取所有的商店信息
	 * 
	 * @return 返回所有的商店信息
	 */
	public List<store> ListStore();

	/**
	 * 通过商店编号获取商店信息
	 * 
	 * @param storeId
	 * @return 返回按条件查询到的商店信息
	 */
	public store getStoreForId(String storeId);

	/**
	 * 通过门店名称获取商店编号
	 * 
	 * @param storeName
	 * @return 返回按条件（名称）查询到的商店编号
	 */
	public String getIdForName(String storeName);

	/**
	 * 添加商店信息
	 * 
	 * @param addobject
	 * @return 添加成功返回true,失败返回false
	 */
	public boolean insertStore(store addobject);

	/**
	 * 删除商店信息
	 * 
	 * @param StoreId
	 * @return 删除成功返回true,失败返回false
	 */
	public boolean deleteStore(String StoreId);

	/**
	 * 修改用户信息
	 * 
	 * @param updatestore
	 * @return 修改成功返回true,失败返回false
	 */
	public boolean updateStore(store updatestore);

	/**
	 * 获取最大的商店编号
	 * 
	 * @return 返回商店的最大编号
	 */
	public String GetMaxId();

	/**
	 * 关闭StoreService
	 */
	public void CloseStoreService();
}
