package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.protocol.Resultset;

import entity.store;

/**
 * DaoStore类 门店类的数据访问类，实现门店信息的数据访问操作
 * 
 * @author chenshaolei 2019年11月27日 上午11:28:09
 */
public class DaoStore extends Database {

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	/**
	 * 获取所有的门店名称
	 * 
	 * @return 返回商店名称
	 */
	public List<String> ListStoresName() {
		List<String> listname = new ArrayList<String>();
		String sql = "select * from store";
		try {
			// 创建实例
			Statement GetAllStore = null;
			GetAllStore = createSta(GetAllStore);
			resuset = (Resultset) GetAllStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String storename = ((ResultSet) resuset).getString("storename");
				listname.add(storename);
			}
			// 关闭实例
			logger.info("获取所有的门店名称-----成功");
			CloseStatement(GetAllStore);
		} catch (Exception e) {
			// System.out.println("获取所有的门店名称失败");
			logger.error("获取所有的门店名称-----失败");
		}
		return listname;
	}

	/**
	 * 根据管理员编号获取所有商店的信息
	 * 
	 * @param regulatorId 管理员编号
	 * @return 返回商店信息
	 */
	public List<store> GetAllStoreByRegulatorId(String regulatorId) {
		List<store> AllStore = new ArrayList<store>();
		String sql = "select * from store where regulatorid=" + regulatorId;
		try {
			// 创建实例
			Statement GetAllStore = null;
			GetAllStore = createSta(GetAllStore);
			resuset = (Resultset) GetAllStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				store object = new store();
				String storeid = ((ResultSet) resuset).getString("storeid");
				String storename = ((ResultSet) resuset).getString("storename");
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				object.setStoreId(storeid);
				object.setStoreName(storename);
				object.setRegulatorId(regulatorid);
				AllStore.add(object);
			}
			logger.info("根据管理员编号获取所有商店的信息-----成功");
			// 关闭实例
			CloseStatement(GetAllStore);
		} catch (Exception e) {
			// System.out.println("根据管理员编号获取所有商店的信息");
			logger.error("根据管理员编号获取所有商店的信息-----失败");
		}
		return AllStore;
	}

	/**
	 * 获取所有的商店信息
	 * 
	 * @return 返回所有的商店信息
	 */
	public List<store> ListStore() {
		List<store> listStore = new ArrayList<store>();
		String sql = "select * from store";
		try {
			// 创建实例
			Statement GetAllStore = null;
			GetAllStore = createSta(GetAllStore);
			resuset = (Resultset) GetAllStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				store object = new store();
				String storeid = ((ResultSet) resuset).getString("storeid");
				String storename = ((ResultSet) resuset).getString("storename");
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				object.setStoreId(storeid);
				object.setStoreName(storename);
				object.setRegulatorId(regulatorid);
				listStore.add(object);
			}
			logger.info("获取所有的商店信息-----成功");
			// 关闭实例
			CloseStatement(GetAllStore);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("获取所有的商店信息-----失败");
		}
		return listStore;
	}

	/**
	 * 通过商店编号获取商店信息
	 * 
	 * @param storeId 门店编号
	 * @return 返回商店信息
	 */
	public store getStoreForId(String storeId) {
		store getstore = new store();
		String sql = "select * from store where storeid='" + storeId + "'";
		try {
			// 创建实例
			Statement GetStore = null;
			GetStore = createSta(GetStore);
			resuset = (Resultset) GetStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				String storeid = ((ResultSet) resuset).getString("storeid");
				String storename = ((ResultSet) resuset).getString("storename");
				String regulatorid = ((ResultSet) resuset).getString("regulatorid");
				getstore.setStoreId(storeid);
				getstore.setStoreName(storename);
				getstore.setRegulatorId(regulatorid);
			}
			logger.info("通过商店编号获取商店信息-----成功");
			// System.out.println("获取商店信息成功");
			// 关闭实例
			CloseStatement(GetStore);
		} catch (Exception e) {
			// System.out.println("获取商店信息失败");
			logger.error("通过商店编号获取商店信息-----失败");
		}
		return getstore;
	}

	/**
	 * 通过门店名称获取商店编号
	 * 
	 * @param storeName 门店名称
	 * @return 返回商店编号
	 */
	public String getIdForName(String storeName) {
		String Id = "";
		String sql = "select * from store where storename='" + storeName + "'";
		try {
			// 创建实例
			Statement GetStore = null;
			GetStore = createSta(GetStore);
			resuset = (Resultset) GetStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				Id = ((ResultSet) resuset).getString("storeid");
			}
			logger.info("通过门店名称获取商店编号-----成功");
			// 关闭实例
			CloseStatement(GetStore);
		} catch (Exception e) {
			// 获取信息失败
			logger.error("通过门店名称获取商店编号-----失败");
		}
		return Id;
	}

	/**
	 * 添加商店信息
	 * 
	 * @param addobject 门店对象
	 * @return 添加成功返回true,失败返回false
	 */
	public boolean insertStore(store addobject) {
		boolean flag = false;
		String Number = "";
		// 判断表中是否存在最大值（表是否为空）
		try {
			if ("".equals(this.GetMaxId())) {
				Number = "0";
			} else {
				Number = this.GetMaxId();
			}
		} catch (Exception e) {
			Number = "0";
		}
		String ID = String.valueOf(Integer.parseInt(Number) + 1);
		String Name = addobject.getStoreName();
		String RegulatorId = addobject.getRegulatorId();
		String sql = "insert into store value('" + ID + "','" + Name + "','" + RegulatorId + "')";
		try {
			// 创建实例
			Statement addstore = null;
			addstore = createSta(addstore);
			addstore.execute(sql);
			// System.out.println("添加商店信息成功");
			flag = true;
			// 关闭实例
			logger.info("添加商店信息-----成功");
			CloseStatement(addstore);
		} catch (Exception e) {
			// System.out.println("添加商店信息失败");
			logger.error("添加商店信息-----失败");
			flag = false;
		}
		return flag;
	}

	/**
	 * 删除商店信息
	 * 
	 * @param StoreId 门店编号
	 * @return 删除成功返回true,失败返回false
	 */
	public boolean deleteStore(String StoreId) {
		boolean flag = false;
		String sql = "delete from store where storeid=" + StoreId;
		try {
			// 创建实例
			Statement delstore = null;
			delstore = createSta(delstore);
			delstore.execute(sql);
			// 关闭实例
			CloseStatement(delstore);
			flag = true;
			logger.info("删除商店信息-----成功");
			// System.out.println("删除商店信息成功");
		} catch (Exception e) {
			flag = false;
			// System.out.println("删除商店信息失败");
			logger.error("删除商店信息-----失败");
		}
		return flag;
	}

	/**
	 * 修改门店信息
	 * 
	 * @param updatestore 门店对象
	 * @return 修改成功返回true,失败返回false
	 */
	public boolean updateStore(store updatestore) {
		boolean flag = false;
		String ID = updatestore.getStoreId();
		String Name = updatestore.getStoreName();
		String RegulatorId = updatestore.getRegulatorId();
		String sql = "update store set storeid='" + ID + "',storename='" + Name + "',regulatorid='" + RegulatorId
				+ "' where storeid='" + ID + "'";
		try {
			// 创建实例
			Statement Updatestore = null;
			Updatestore = createSta(Updatestore);
			Updatestore.execute(sql);
			flag = true;
			logger.info("修改用户信息-----成功");
			// System.out.println("修改商店信息成功");
			// 关闭实例
			CloseStatement(Updatestore);
		} catch (Exception e) {
			flag = false;
			// System.out.println("修改商店信息失败");
			logger.error("修改用户信息-----失败");
		}
		return flag;
	}

	/**
	 * 获取最大的商店编号
	 * 
	 * @return 返回商店的最大编号
	 */
	public String GetMaxId() {
		List<Integer> listId = new ArrayList<Integer>();
		String sql = "select * from store";
		try {
			// 创建实例
			Statement GetStore = null;
			GetStore = createSta(GetStore);
			resuset = (Resultset) GetStore.executeQuery(sql);
			while (((ResultSet) resuset).next()) {
				listId.add(Integer.parseInt(((ResultSet) resuset).getString("storeid")));
			}
			logger.info("获取最大的商店编号-----成功");
			// System.out.println("获取信息成功");
			// 关闭实例
			CloseStatement(GetStore);
		} catch (Exception e) {
			// System.out.println("获取信息失败");
			logger.error("获取最大的商店编号-----失败");
		}
		return Collections.max(listId).toString();
	}
}
