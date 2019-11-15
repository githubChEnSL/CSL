package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import entity.store;

public class DatabaseStore extends Database{
	
	/**根据管理员编号获取所有商店的信息*/
	public List<store> GetAllStoreByRegulatorId(String regulatorId) {
		List<store> AllStore = new ArrayList<store>();
		String sql="select * from store where regulatorid="+regulatorId;
		try {
			//创建实例
			Statement GetAllStore=null;
			GetAllStore=createSta(GetAllStore);
			resuset=(Resultset) GetAllStore.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				store object=new store();
				String storeid=((ResultSet) resuset).getString("storeid");
				String storename=((ResultSet) resuset).getString("storename");
				String regulatorid=((ResultSet) resuset).getString("regulatorid");
				object.setStoreId(storeid);
				object.setStoreName(storename);
				object.setRegulatorId(regulatorid);
				AllStore.add(object);
			}
			//关闭实例
			 CloseStatement(GetAllStore);
		} catch (Exception e) {
			System.out.println("获取用户角色名称失败");
		}
		return AllStore;
	}
	/**获取所有的商店信息*/
	public List<store> ListStore(){
		List<store> listStore=new ArrayList<store>();
		String sql="select * from store";
		try {
			//创建实例
			Statement GetAllStore=null;
			GetAllStore=createSta(GetAllStore);
			resuset= (Resultset) GetAllStore.executeQuery(sql);
			 while(((ResultSet) resuset).next()){
				store object=new store();
				String storeid=((ResultSet) resuset).getString("storeid");
				String storename=((ResultSet) resuset).getString("storename");
				String regulatorid=((ResultSet) resuset).getString("regulatorid");
				object.setStoreId(storeid);
				object.setStoreName(storename);
				object.setRegulatorId(regulatorid);
				listStore.add(object);
		      }
			//关闭实例
			 CloseStatement(GetAllStore);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStore;
	}
	/**通过商店编号获取上商店信息*/
	public store getStoreForId(String storeId) {
		store getstore=new store();
		String sql="select * from store where storeid="+storeId;
		try {
			//创建实例
			Statement GetStore=null;
			GetStore=createSta(GetStore);
			resuset=(Resultset) GetStore.executeQuery(sql);
			while(((ResultSet) resuset).next()) {
				String storeid=((ResultSet) resuset).getString("storeid");
				String storename=((ResultSet) resuset).getString("storename");
				String regulatorid=((ResultSet) resuset).getString("regulatorid");
				getstore.setStoreId(storeid);
				getstore.setStoreName(storename);
				getstore.setRegulatorId(regulatorid);
			}
			System.out.println("获取商店信息成功");
			//关闭实例
			 CloseStatement(GetStore);
		} catch (Exception e) {
			System.out.println("获取商店信息失败");
		}
		return getstore;
	}
	/**添加商店信息*/
	public void insertStore(store addobject) {
		String ID=addobject.getStoreId();
		String Name=addobject.getStoreName();
		String RegulatorId=addobject.getRegulatorId();
		String sql="insert into store value("+ID+",'"+Name+"','"+RegulatorId+"')";
		try {
			//创建实例
			Statement addstore=null;
			addstore=createSta(addstore);
			addstore.execute(sql);
			System.out.println("添加商店信息成功");
			//关闭实例
			CloseStatement(addstore);
		} catch (Exception e) {
			System.out.println("添加商店信息失败");
		}
	}
	/**删除用户信息*/
	public void deleteStore(String StoreId) {
		String sql="delete from store where storeid="+StoreId;
		try {
			//创建实例
			Statement delstore=null;
			delstore=createSta(delstore);
			delstore.execute(sql);
			//关闭实例
			CloseStatement(delstore);
			System.out.println("删除商店信息成功");
		} catch (Exception e) {
			System.out.println("删除商店信息失败");
		}
	}
	/**修改用户信息*/
	public void updateStore(store updatestore) {
		String ID=updatestore.getStoreId();
		String Name=updatestore.getStoreName();
		String RegulatorId=updatestore.getRegulatorId();
		String sql="update store set storeid='"+ID+"',storename='"+Name+"',regulatorid='"+RegulatorId+"' where storeid='"+ID+"'";
		try {
			//创建实例
			Statement Updatestore=null;
			Updatestore=createSta(Updatestore);
			Updatestore.execute(sql);
			System.out.println("修改商店信息成功");
			//关闭实例
			CloseStatement(Updatestore);
		} catch (Exception e) {
			System.out.println("修改商店信息失败");
		}	
	}
}
