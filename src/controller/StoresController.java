package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import entity.store;
import service.RegulatorService;
import service.StoreService;
import service.impl.RegulatorServiceImpl;
import service.impl.StoreServiceImpl;

/**
 * StoresControllerl类 实现门店信息管理
 * 
 * @author chenshaolei 2019年11月27日 上午11:45:39
 */
public class StoresController extends HttpServlet {

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	private static final long serialVersionUID = 1L;

	/**
	 * StoresController的构造函数
	 */
	public StoresController() {
		super();
	}

	/**
	 * doGet函数，与前端进行数据交互
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("门店信息管理");
		// 设置字符编号
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// 接收前台传来的action
		String action = request.getParameter("action");
		// 接收前台传来的StoreName
		String StoreName = request.getParameter("StoreName");
		// 接收RegulatorName
		String RegulatorName = request.getParameter("RegulatorName");
		// 获取所有的门店信息
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		// 获取前台传来的StoreId
		String StoreId = request.getParameter("StoreId");
		// 定义service实现类的对象
		RegulatorService RegulatorService = new RegulatorServiceImpl();
		StoreService StoreService = new StoreServiceImpl();
		// 保存提示信息
		String msgString = "";
		if ("add".equals(action)) {
			// System.out.println("StoreName:" + StoreName + " RegulatorName:" +
			// RegulatorName + " action:" + action);
			/** 封装添加的对象 */
			store addobject = new store();
			try {
				// 判断门店名称是否有重复
				if ("".equals(StoreService.getIdForName(StoreName))) {
					// 没有重复
					// 验证管理员是否已经存在
					if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
						/** 不存在管理员 */
						// 添加管理员
						regulator addreglRegulator = new regulator();
						addreglRegulator.setRegulatorName(RegulatorName);
						addreglRegulator.setRegulatorRoleId("2");
						// 添加的管理员所属部门默认为最新添加的门店编号
						Integer maxStoreId = Integer.parseInt(StoreService.GetMaxId()) + 1;
						addreglRegulator.setStoreId(maxStoreId.toString());
						RegulatorService.insertRegulator(addreglRegulator);
						// 添加门店信息
						addobject.setStoreName(StoreName);
						addobject.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
						StoreService.insertStore(addobject);
						msgString = "添加成功";
					} else {
						/** 存在管理員 */
						// 添加门店信息
						addobject.setStoreName(StoreName);
						addobject.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
						StoreService.insertStore(addobject);
						msgString = "添加成功";
					}
				} else {
					// 名称重复
					msgString = "名称重复，添加失败";
				}
			} catch (Exception e) {
				msgString = "添加失败";
			}
		} else if ("delete".equals(action)) {
			// System.out.println("StoreId:" + StoreId + " action:" + action);
			if (StoreService.deleteStore(StoreId)) {
				msgString = "删除成功";
			} else {
				msgString = "删除失败";
			}
		} else if ("update".equals(action)) {
			// System.out.println("StoreId:" + StoreId + " StoreName:" + StoreName + "
			// RegulatorName:" + RegulatorName
			// + " action:" + action);
			// 由ID获取原来的门店信息
			store oldStore = StoreService.getStoreForId(StoreId);
			// 定义新的门店信息
			store newStore = new store();
			// 写入ID
			newStore.setStoreId(StoreId);
			// 判断名称是否由变化
			if (StoreName.equals(oldStore.getStoreName())) {
				// 名称没变
				// 写入名称
				newStore.setStoreName(oldStore.getStoreName());
				// 判断管理员名称是否由变化
				// 输入的管理员名称是否已经存在
				if (RegulatorService.GetRegulatorRoleId(RegulatorName) != null) {
					// 存在
					if (RegulatorService.GetRegulatorRoleId(RegulatorName).equals(oldStore.getRegulatorId())) {
						// 管理员没变（写入管理员）
						newStore.setRegulatorId(oldStore.getRegulatorId());
						msgString = "修改成功";
						StoreService.updateStore(newStore);
					} else {
						// 管理员变了（判断管理员是否已经存在）
						if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
							// 管理员不存在（添加管理员）
							regulator addreglRegulator = new regulator();
							addreglRegulator.setRegulatorName(RegulatorName);
							// 角色默认为店长（管理员）
							addreglRegulator.setRegulatorRoleId("2");
							RegulatorService.insertRegulator(addreglRegulator);
							// 写入管理员
							newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
							StoreService.updateStore(newStore);
							msgString = "修改成功";
						} else {
							// 管理员存在（写入管理员）
							newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
							StoreService.updateStore(newStore);
							msgString = "修改成功";
						}
					}
				} else {
					// 不存在
					if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
						// 管理员不存在（添加管理员）
						regulator addreglRegulator = new regulator();
						addreglRegulator.setRegulatorName(RegulatorName);
						// 角色默认为店长（管理员）
						addreglRegulator.setRegulatorRoleId("2");
						RegulatorService.insertRegulator(addreglRegulator);
						// 写入管理员
						newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
						StoreService.updateStore(newStore);
						msgString = "修改成功";
					} else {
						// 管理员存在（写入管理员）
						newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
						StoreService.updateStore(newStore);
						msgString = "修改成功";
					}
				}
			} else {
				// 名称变了（判断是否和其他的名称重复）
				// 由名称获取信息
				if ("".equals(StoreService.getIdForName(StoreName))) {
					// 名称没重复
					// 写入名称
					newStore.setStoreName(StoreName);
					// 判断管理员名称是否有变化
					// 输入的管理员名称是否已经存在
					if (RegulatorService.GetRegulatorRoleId(RegulatorName) != null) {
						// 存在
						if (RegulatorService.GetRegulatorRoleId(RegulatorName).equals(oldStore.getRegulatorId())) {
							// 管理员没变（写入管理员）
							newStore.setRegulatorId(oldStore.getRegulatorId());
							msgString = "修改成功";
							StoreService.updateStore(newStore);
							// System.err.println("管理员没变");
						} else {
							// 管理员变了（判断管理员是否已经存在）
							// System.err.println("管理员变了");
							if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
								// 管理员不存在（添加管理员信息）
								regulator addreglRegulator = new regulator();
								addreglRegulator.setRegulatorName(RegulatorName);
								// 角色默认为店长（管理员）
								addreglRegulator.setRegulatorRoleId("2");
								RegulatorService.insertRegulator(addreglRegulator);
								// 写入管理员
								newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
								StoreService.updateStore(newStore);
								msgString = "修改成功";
							} else {
								// 管理员存在（写入管理员）
								newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
								StoreService.updateStore(newStore);
								msgString = "修改成功";
							}
						}
					} else {
						// 不存在
						newStore.setStoreName(StoreName);
						if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
							// 管理员不存在（添加管理员信息）
							regulator addreglRegulator = new regulator();
							addreglRegulator.setRegulatorName(RegulatorName);
							// 角色默认为店长（管理员）
							addreglRegulator.setRegulatorRoleId("2");
							RegulatorService.insertRegulator(addreglRegulator);
							// 写入管理员
							newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
							StoreService.updateStore(newStore);
							msgString = "修改成功";
						} else {
							// 管理员存在（写入管理员）
							newStore.setRegulatorId(RegulatorService.GetIdForName(RegulatorName));
							StoreService.updateStore(newStore);
							msgString = "修改成功";
						}
					}
				} else {
					// 名称重复
					msgString = "名称重复，修改失败";
				}
			}
		} else {
			// System.out.println("查询所有的门店信息");
			List<store> listStores = StoreService.ListStore();
			for (int i = 0; i < listStores.size(); i++) {
				Map<String, Object> row = new HashMap<>();
				store entity = listStores.get(i);
				row.put("StoreNum", entity.getStoreId());
				row.put("StoreName", entity.getStoreName());
				row.put("RegulatorName",
						RegulatorService.GetRegulatorForId(entity.getRegulatorId()).getRegulatorName());
				storeData.add(row);
			}
		}
		/** 封装返回前端的Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("rows", storeData);// 门店数据
		preparedata.put("msg", msgString);// 提示信息
		// 将map转为json
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		RegulatorService.CloseRegulatorService();
		StoreService.CloseStoreService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
