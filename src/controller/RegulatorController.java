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

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import jdbc.DatabaseRegulator;

public class RegulatorController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// 获取前台的action
		String action = request.getParameter("action");
		// 获取前台传来的regulatorName
		String RegulatorName = request.getParameter("regulatorName");
		// 获取前台传来的RegulatorRoleName
		String RegulatorRoleName = request.getParameter("RegulatorRoleName");
		// 获取所有的门店信息
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		// 获取前台传来的RegulatorId
		String RegulatorId = request.getParameter("RegulatorId");
		// 定义与数据库交互的对象
		DatabaseRegulator ReguRegulatorData = new DatabaseRegulator();
		// 保存提示信息
		String msgString = "";
		// 判断登陆者的身份
		HttpSession session = request.getSession();
		regulator loginRegulator = new regulator();
		loginRegulator = ReguRegulatorData.GetRegulatorForId(session.getAttribute("name").toString());
		String RoleId = loginRegulator.getRegulatorRoleId();
		if ("add".equals(action)) {
			System.out.println("RegulatorName:" + RegulatorName + " RegulatorRoleName:" + RegulatorRoleName
					+ "  action:" + action);
			/** 封装添加的对象 */
			regulator addobject = new regulator();
			try {
				// 判断添加的名称是否有重复
				if ("".equals(ReguRegulatorData.GetIdForName(RegulatorName))) {
					// 没有重复（可以添加）
					// 判断登陆者的权限
					if (RoleId.equals("1")) {
						// 超级管理员可以随意添加
						addobject.setRegulatorName(RegulatorName);
						addobject.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
						ReguRegulatorData.insertRegulator(addobject);
						msgString = "添加成功";
					} else if (RoleId.equals("2")) {
						// 管理员（店长）只能添加普通员工
						if (ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
							addobject.setRegulatorName(RegulatorName);
							addobject.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
							ReguRegulatorData.insertRegulator(addobject);
							msgString = "添加成功";
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
			System.out.println("RegulatorId:" + RegulatorId + "  action:" + action);
			if (ReguRegulatorData.deleteRegulator(RegulatorId)) {
				msgString = "删除成功";
			} else {
				msgString = "删除失败";
			}
		} else if ("update".equals(action)) {
			System.out.println("RegulatorId:" + RegulatorId + " RegulatorName:" + RegulatorName + " RegulatorRoleName:"
					+ RegulatorRoleName + "  action:" + action);
			// 由ID获取原来的门店信息
			regulator oldRegulator=ReguRegulatorData.GetRegulatorForId(RegulatorId);
			//封装修改信息
			regulator newRegulator=new regulator();
			//写入ID
			newRegulator.setRegulatorId(RegulatorId);
			//判断能否更改管理员角色
			if(RoleId.equals("1")) {
				//任意修改
				//写入名称
				newRegulator.setRegulatorName(RegulatorName);
				//写入旧密码
				newRegulator.setPassword(oldRegulator.getPassword());
				//写入角色编号
				newRegulator.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
				ReguRegulatorData.updateRegulator(newRegulator);
				msgString="修改成功";
			}else if(RoleId.equals("2")) {
				//只能修改员工信息
				if(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName).equals("3")) {
					//写入名称
					newRegulator.setRegulatorName(RegulatorName);
					//写入旧密码
					newRegulator.setPassword(oldRegulator.getPassword());
					//写入角色编号
					newRegulator.setRegulatorRoleId(ReguRegulatorData.GetRegulatorRoleId(RegulatorRoleName));
					ReguRegulatorData.updateRegulator(newRegulator);
					msgString="修改成功";
				}else {
					msgString="修改失败，您没有权限";
				}
			}else {
				msgString="修改失败，您没有修改权限";
			}
		} else {
			// 根据身份返回数据
			// 1:超级管理员2.管理员（店主）3.普通员工
			List<regulator> listregulator = new ArrayList<regulator>();
			if (RoleId.equals("1")) {
				System.err.println("超级管理员");
				// 获取管理员
				List<regulator> list1 = ReguRegulatorData.ListRegulator();
				// 获取普通员工
				List<regulator> list2 = ReguRegulatorData.listOrdinaryRegulators();
				listregulator.addAll(list1);
				listregulator.addAll(list2);
			} else if (RoleId.equals("2")) {
				System.err.println("管理员");
				listregulator = ReguRegulatorData.listOrdinaryRegulators();
			} else {
				;
			}
			if (listregulator.size() != 0) {
				for (int i = 0; i < listregulator.size(); i++) {
					Map<String, Object> row = new HashMap<>();
					regulator entity = listregulator.get(i);
					row.put("RegulatorNum", entity.getRegulatorId());
					row.put("RegulatorName", entity.getRegulatorName());
					row.put("RegulatorRole", ReguRegulatorData.GetRegulatorRoleName(entity.getRegulatorRoleId()));
					storeData.add(row);
				}
			} else {
				storeData = null;
			}
		}
		/** 封装返回前端的Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("rows", storeData);// 数据
		preparedata.put("msg", msgString);// 提示信息
		// 将map转为json
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		ReguRegulatorData.CloseDatabase();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
