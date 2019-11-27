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
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import service.RegulatorService;
import service.StoreService;
import service.impl.RegulatorServiceImpl;
import service.impl.StoreServiceImpl;

/**
 * RegulatorController类 员工信息管理类
 * 
 * @author chenshaolei 2019年11月27日 上午11:41:52
 */
public class RegulatorController extends HttpServlet {

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	private static final long serialVersionUID = 1L;

	/**
	 * doGet函数，与前端进行数据交互
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("管理员信息管理");
		// 设置字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// 获取前台传来的action
		String action = request.getParameter("action");
		// 获取前台传来的regulatorName
		String RegulatorName = request.getParameter("regulatorName");
		// 获取前台传来的RegulatorRoleName
		String RegulatorRoleName = request.getParameter("RegulatorRoleName");
		// 获取前台传来的StoreName
		String StoreName = request.getParameter("StoreName");
		// 获取所有的管理员信息
		List<Map<String, Object>> RegulatorData = new ArrayList<Map<String, Object>>();
		// 获取前台传来的RegulatorId
		String RegulatorId = request.getParameter("RegulatorId");
		// 定义service实现类的对象
		RegulatorService RegulatorService = new RegulatorServiceImpl();
		StoreService StoreService = new StoreServiceImpl();
		// 获取所有的门店名称
		List<String> listStoreName = StoreService.ListStoresName();
		// 获取所有的管理者角色名称
		List<String> listRoleName = RegulatorService.ListRegulatorRoleName();
		// 保存提示信息
		String msgString = "";
		// 封装返回的数据
		List<regulator> listregulator = new ArrayList<regulator>();
		// 判断登录者的身份
		HttpSession session = request.getSession();
		regulator loginRegulator = new regulator();
		// 验证用户是否登录
		try {
			loginRegulator = RegulatorService.GetRegulatorForId(session.getAttribute("id").toString());
			String RoleId = loginRegulator.getRegulatorRoleId();
			if ("add".equals(action)) {
				// System.out.println("RegulatorName:" + RegulatorName + " RegulatorRoleName:" +
				// RegulatorRoleName + " StoreName:" + StoreName + " action:" + action);
				/** 封装添加的对象 */
				regulator addobject = new regulator();
				try {
					// 判断添加的名称是否有重复
					if ("".equals(RegulatorService.GetIdForName(RegulatorName))) {
						// 没有重复
						// 判断登录者的权限
						if (RoleId.equals("1")) {
							// 超级管理员可以随意添加
							addobject.setRegulatorName(RegulatorName);
							addobject.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
							addobject.setStoreId(StoreService.getIdForName(StoreName));
							RegulatorService.insertRegulator(addobject);
							msgString = "添加成功";
						} else if (RoleId.equals("2")) {
							// 管理员（店长）只能添加普通员工
							if (RegulatorService.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
								// 是否为本门店
								if (StoreService.getIdForName(StoreName).equals(loginRegulator.getStoreId())) {
									addobject.setRegulatorName(RegulatorName);
									addobject
											.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
									addobject.setStoreId(StoreService.getIdForName(StoreName));
									RegulatorService.insertRegulator(addobject);
									msgString = "添加成功";
								} else {
									msgString = "添加失败，您无法添加其他门店的员工";
								}
							} else {
								msgString = "添加失败，您没有权限添加";
							}
						} else {
							// 其他都不允许添加
							msgString = "添加失败，您没有权限添加";
						}
					} else {
						// 名称重复
						msgString = "名称重复，添加失败";
					}
				} catch (Exception e) {
					msgString = "添加失败";
				}
			} else if ("delete".equals(action)) {
				// System.out.println("RegulatorId:" + RegulatorId + " action:" + action);
				if (RegulatorService.deleteRegulator(RegulatorId)) {
					msgString = "删除成功";
				} else {
					msgString = "删除失败";
				}
			} else if ("update".equals(action)) {
				// System.out.println("RegulatorId:" + RegulatorId + " RegulatorName:" +
				// RegulatorName
				// + " RegulatorRoleName:" + RegulatorRoleName + " StoreName:" + StoreName + "
				// action:" + action);
				// 有ID获取原来的门店信息
				regulator oldRegulator = RegulatorService.GetRegulatorForId(RegulatorId);
				// 封装修改信息
				regulator newRegulator = new regulator();
				// 写入ID
				newRegulator.setRegulatorId(RegulatorId);
				// 判断能否更改管理员角色
				if (RoleId.equals("1")) {
					// 任意修改
					// 写入名称
					newRegulator.setRegulatorName(RegulatorName);
					// 写入旧密码
					newRegulator.setPassword(oldRegulator.getPassword());
					// 写入角色编号
					newRegulator.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
					// 写入门店编号
					newRegulator.setStoreId(StoreService.getIdForName(StoreName));
					RegulatorService.updateRegulator(newRegulator);
					msgString = "修改成功";
				} else if (RoleId.equals("2")) {
					// 只能修改员工信息
					if (RegulatorService.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
						// 写入名称
						newRegulator.setRegulatorName(RegulatorName);
						// 写入旧密码
						newRegulator.setPassword(oldRegulator.getPassword());
						// 写入角色编号
						newRegulator.setRegulatorRoleId(RegulatorService.GetRegulatorRoleId(RegulatorRoleName));
						// 写入门店编号
						newRegulator.setStoreId(StoreService.getIdForName(StoreName));
						RegulatorService.updateRegulator(newRegulator);
						msgString = "修改成功";
					} else {
						msgString = "修改失败，您没有权限";
					}
				} else {
					msgString = "修改失败，您没有修改权限";
				}
			} else {
				if ("search".equals(action)) {
					// 超级管理员身份
					// System.err.println("action:" + action + " StoreName:" + StoreName);
					if (RoleId.equals("1")) {
						if ("所有".equals(StoreName)) {
							listregulator = RegulatorService.ListRegulator();
						} else {
							// 根据门店名称获取门店编号
							String StoreId = StoreService.getIdForName(StoreName);
							listregulator = RegulatorService.listRegulatorByStoreId(StoreId);
						}
					} else if (RoleId.equals("2")) {
						// 管理员身份
						// 获取本门店的所有员工信息
						String StoreId = loginRegulator.getStoreId();
						listregulator = RegulatorService.listRegulatorByStoreId(StoreId);
					} else {
						listregulator = null;
					}
				} else {
					// 管理员身份
					// 获取本门店的所有员工信息
					String StoreId = loginRegulator.getStoreId();
					listregulator = RegulatorService.listRegulatorByStoreId(StoreId);
				}
			}
			if (listregulator.size() != 0) {
				for (int i = 0; i < listregulator.size(); i++) {
					Map<String, Object> row = new HashMap<>();
					regulator entity = listregulator.get(i);
					if (entity.getRegulatorRoleId().equals("1")) {
						if (RoleId.equals("1")) {
							row.put("RegulatorNum", entity.getRegulatorId());
							row.put("RegulatorName", entity.getRegulatorName());
							row.put("RegulatorRole",
									RegulatorService.GetRegulatorRoleName(entity.getRegulatorRoleId()));
							if ("".equals(entity.getStoreId())) {
								row.put("StoreName", "空");
							} else {
								row.put("StoreName", StoreService.getStoreForId(entity.getStoreId()).getStoreName());
							}
							RegulatorData.add(row);
						} else {

						}
					} else {
						row.put("RegulatorNum", entity.getRegulatorId());
						row.put("RegulatorName", entity.getRegulatorName());
						row.put("RegulatorRole", RegulatorService.GetRegulatorRoleName(entity.getRegulatorRoleId()));
						if ("".equals(entity.getStoreId())) {
							row.put("StoreName", "空");
						} else {
							row.put("StoreName", StoreService.getStoreForId(entity.getStoreId()).getStoreName());
						}
						RegulatorData.add(row);
					}
				}
			} else {
				// System.out.println("nullllllll..............");
			}
		} catch (Exception e) {
			response.sendRedirect("Exit.jsp");
		}
		/** 封装返回前端的Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("liststorename", listStoreName);// 门店名称
		preparedata.put("rows", RegulatorData);// 员工数据
		preparedata.put("msg", msgString);// 提示信息
		preparedata.put("listRoleName", listRoleName);// 角色名称
		// 将mapת转为json
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		StoreService.CloseStoreService();
		RegulatorService.CloseRegulatorService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
