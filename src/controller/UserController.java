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

import com.alibaba.fastjson.JSONObject;

import entity.user;
import jdbc.DatabaseUser;

public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// 获取前台的action
		String action = request.getParameter("action");
		// 获取前台传来的UserName
		String UserName = request.getParameter("UserName");
		// 获取前台传来的RoleName
		String RoleName = request.getParameter("RoleName");
		// 获取所有的门店信息
		List<Map<String, Object>> storeData = new ArrayList<Map<String, Object>>();
		// 获取前台传来的UserNum
		String UserNum = request.getParameter("UserNum");
		// 定义与数据库交互的对象
		DatabaseUser UserData = new DatabaseUser();
		// 保存提示信息
		String msgString = "";
		if ("add".equals(action)) {
			System.out.println("UserName:" + UserName + " RoleName:" + RoleName + "  action:" + action);
			/** 封装添加的对象 */
			user addobject = new user();
			try {
				// 判断名称是否有重复
				if ("".equals(UserData.getUserIdForName(UserName))) {
					// 没有重复（可以添加）
					addobject.setUserName(UserName);
					addobject.setRoleId(UserData.GetUserRoleId(RoleName));
					UserData.insertUser(addobject);
					msgString = "添加成功";
				} else {
					// 名称重复
					msgString = "名称重复，添加失败";
				}
			} catch (Exception e) {
				msgString = "添加失败";
			}
		} else if ("delete".equals(action)) {
			System.out.println("UserNum:" + UserNum + "  action:" + action);
			if (UserData.deleteUser(UserNum)) {
				msgString = "删除成功";
			} else {
				msgString = "删除失败";
			}
		} else if ("update".equals(action)) {
			System.out.println("UserNum:" + UserNum+ " UserName:" + UserName  + " RoleName:" + RoleName + "  action:" + action);
			// 由ID获取原来的会员信息
			user oldUser = UserData.getUserForId(UserNum);
			// 定义新的会员信息
			user newUser = new user();
			// 写入ID
			newUser.setUserId(UserNum);
			// 判断名称是否有变化
			if (UserName.equals(oldUser.getUserName())) {
				// 名称没变（写入名称）
				newUser.setUserName(oldUser.getUserName());
				//写入会员角色
				newUser.setRoleId(UserData.GetUserRoleId(RoleName));
				//修改会员信息
				UserData.updateUser(newUser);
				msgString="修改成功";
			} else {
				// 名称变了（判断是否和其他的名称重复）
				// 由名称获取信息
				if ("".equals(UserData.getUserIdForName(UserName))) {
					// 名称没重复（写入名称）
					newUser.setUserName(UserName);
					//写入会员角色
					newUser.setRoleId(UserData.GetUserRoleId(RoleName));
					//修改会员信息
					UserData.updateUser(newUser);
					msgString="修改成功";
				} else {
					// 名称重复
					msgString = "名称重复，修改失败";
				}
			}
		} else {
			System.out.println();
			System.out.println("查询所有的会员信息");
			List<user> listStores = UserData.ListUser();
			for (int i = 0; i < listStores.size(); i++) {
				Map<String, Object> row = new HashMap<>();
				user entity = listStores.get(i);
				row.put("UserNum", entity.getUserId());
				row.put("UserName", entity.getUserName());
				row.put("UserRoleName", UserData.GetUserRoleName(entity.getRoleId()));
				storeData.add(row);
			}
		}
		/** 封装返回前端的Map */
		Map<String, Object> preparedata = new HashMap<String, Object>();
		preparedata.put("rows", storeData);
		preparedata.put("msg", msgString);
		// 将map转为json
		JSONObject data = new JSONObject(preparedata);
		PrintWriter out = response.getWriter();
		/** 封装返回提示信息 */
		out.print(data);
		out.flush();
		out.close();
		UserData.CloseDatabase();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
