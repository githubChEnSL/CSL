package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
 * PersonalController类 实现个人信息管理类
 * 
 * @author chenshaolei 2019年11月27日 上午11:43:01
 */
public class PersonalController extends HttpServlet {

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	private static final long serialVersionUID = 1L;

	/**
	 * PersonalController的构造函数
	 */
	public PersonalController() {
		super();
	}

	/**
	 * doGet函数，与前端进行数据交互
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("个人信息管理");
		// 设置字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// 定义service实现类的对象
		RegulatorService RegulatorService = new RegulatorServiceImpl();
		StoreService StoreService = new StoreServiceImpl();
		// 封装返回的提示信息
		String msgString = "";
		// 获取session域中的id(因为修改的个人信息就是登录者的信息)
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("id");
		// 获取前台的Type
		String Type = request.getParameter("Type");
		// 获取前台的updateName
		String updateName = request.getParameter("updateName");
		// 获取前台的oldpassword
		String oldpassword = request.getParameter("oldpassword");
		// 获取前台的truepassword
		String truepassword = request.getParameter("truepassword");
		// 由id获取旧对象
		regulator oldRegulator = RegulatorService.GetRegulatorForId(loginid);
		/** 封装修改的数据 */
		regulator newRegulator = new regulator();
		if ("updatename".equals(Type)) {
			// System.err.println("修改名称" + updateName);
			if ("".equals(updateName)) {
				msgString = "名称不能为空";
			} else {
				newRegulator.setRegulatorId(oldRegulator.getRegulatorId());
				newRegulator.setRegulatorName(updateName);
				newRegulator.setPassword(oldRegulator.getPassword());
				newRegulator.setRegulatorRoleId(oldRegulator.getRegulatorRoleId());
				newRegulator.setStoreId(oldRegulator.getStoreId());
				RegulatorService.updateRegulator(newRegulator);
				msgString = "修改名称成功";
			}
		} else if ("updatepass".equals(Type)) {
			// 判断旧密码是否正确
			if (oldpassword.equals(RegulatorService.GetRegulatorForId(loginid).getPassword())) {
				newRegulator.setRegulatorId(oldRegulator.getRegulatorId());
				newRegulator.setRegulatorName(oldRegulator.getPassword());
				newRegulator.setPassword(truepassword);
				newRegulator.setRegulatorRoleId(oldRegulator.getRegulatorRoleId());
				newRegulator.setStoreId(oldRegulator.getStoreId());
				RegulatorService.updateRegulator(newRegulator);
				msgString = "修改密码成功";
			} else {
				msgString = "旧密码不正确，修改失败";
			}
		} else {
			msgString = "啥都没改";
		}
		regulator object = RegulatorService.GetRegulatorForId(loginid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LoginId", object.getRegulatorId());
		map.put("LoginName", object.getRegulatorName());
		map.put("LoginStoreName", StoreService.getStoreForId(object.getStoreId()).getStoreName());
		map.put("LoginRoleName", RegulatorService.GetRegulatorRoleName(object.getRegulatorRoleId()));
		map.put("msg", msgString);
		PrintWriter out = response.getWriter();
		// 将map转为json
		JSONObject data = new JSONObject(map);
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
