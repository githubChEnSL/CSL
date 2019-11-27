package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.regulator;
import service.RegulatorService;
import service.impl.RegulatorServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	// 管理员登陆
	public static Map<String, Object> RegulatorLogin(String RegulatorNum, String Password) {
		logger.info("管理员登陆");
		boolean flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		// 定义与service实现类的对象
		RegulatorService RegulatorService = new RegulatorServiceImpl();
		Integer roleId = -1;
		String loginName = "";
		try {
			regulator getObject = RegulatorService.GetRegulatorForId(RegulatorNum);
			if (Integer.parseInt(getObject.getRegulatorRoleId()) == 3) {
				flag = false;
			} else {
				if (getObject.getRegulatorId() != null) {
					if (getObject.getPassword().equals(Password)) {
						roleId = Integer.parseInt(getObject.getRegulatorRoleId());
						loginName = getObject.getRegulatorName();
						flag = true;
					} else {
						flag = false;
					}
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		map.put("flag", flag);
		map.put("loginName", loginName);
		map.put("roleid", roleId);
		RegulatorService.CloseRegulatorService();
		return map;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("userName");
		String password = request.getParameter("password");
		String error = "";
		Map<String, Object> loginMap = RegulatorLogin(id, password);
		boolean flag = (boolean) loginMap.get("flag");
		//System.err.println(flag);
		Integer roleid = (Integer) loginMap.get("roleid");
		String loginName = (String) loginMap.get("loginName");
		if (flag) {
			//System.out.println("登陆请求----登陆成功");
			//System.err.println(roleid);
			logger.info("登陆请求----登陆成功");
			request.getSession().setAttribute("roleId", roleid);
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("loginName", loginName);
			request.getSession().setAttribute("error", "");
			response.sendRedirect("index.jsp");
		} else {
			//System.out.println("登陆请求----登陆失败");
			logger.error("登陆请求----登陆失败");
			error = "账号或密码错误,您无权登陆系统";
			request.getSession().setAttribute("error", error);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
