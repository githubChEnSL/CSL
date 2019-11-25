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

import com.alibaba.fastjson.JSONObject;

import entity.regulator;
import service.RegulatorService;
import service.StoreService;

public class PersonalController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PersonalController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//定义Service对象
		RegulatorService regulaService = new RegulatorService();
		StoreService storeService = new StoreService();
		//封裝返回提示信息
		String msgString="";
		// 获取session域中得id(因为修改的是个人信息即登录者的信息)
		HttpSession session = request.getSession();
		String loginid = (String) session.getAttribute("id");
		// 获取前台的Type
		String Type = request.getParameter("Type");
		// 获取前台的Type
		String updateName = request.getParameter("updateName");
		//获取前台的oldpassword
		String oldpassword=request.getParameter("oldpassword");
		//获取前台的truepassword
		String truepassword=request.getParameter("truepassword");
		//由id获取旧对象
		regulator oldRegulator=regulaService.GetRegulatorForId(loginid);
		/**封装修改的数据*/
		regulator newRegulator=new regulator();
		if("updatename".equals(Type)) {
			System.err.println("修改名称"+updateName);
			if("".equals(updateName)) {
				msgString="名称不能为空";
			}else {
				newRegulator.setRegulatorId(oldRegulator.getRegulatorId());
				newRegulator.setRegulatorName(updateName);
				newRegulator.setPassword(oldRegulator.getPassword());
				newRegulator.setRegulatorRoleId(oldRegulator.getRegulatorRoleId());
				newRegulator.setStoreId(oldRegulator.getStoreId());
				regulaService.updateRegulator(newRegulator);
				msgString="修改名称成功";
			}
		}else if("updatepass".equals(Type)) {
			//判断旧密码是否正确
			if(oldpassword.equals(regulaService.GetRegulatorForId(loginid).getPassword())) {
				newRegulator.setRegulatorId(oldRegulator.getRegulatorId());
				newRegulator.setRegulatorName(oldRegulator.getPassword());
				newRegulator.setPassword(truepassword);
				newRegulator.setRegulatorRoleId(oldRegulator.getRegulatorRoleId());
				newRegulator.setStoreId(oldRegulator.getStoreId());
				regulaService.updateRegulator(newRegulator);
				msgString="修改密码成功";
			}else {
				msgString="旧密码不正确，修改失败";
			}
		}else {
			msgString="啥都没改";
		}
		
		regulator object = regulaService.GetRegulatorForId(loginid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LoginId", object.getRegulatorId());
		map.put("LoginName", object.getRegulatorName());
		map.put("LoginStoreName", storeService.getStoreForId(object.getStoreId()).getStoreName());
		map.put("LoginRoleName", regulaService.GetRegulatorRoleName(object.getRegulatorRoleId()));
		map.put("msg", msgString);
		PrintWriter out = response.getWriter();
		// 将map转为json
		JSONObject data = new JSONObject(map);
		out.print(data);
		out.flush();
		out.close();
		storeService.closeStoreService();
		regulaService.CloseRegulatorService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
}
