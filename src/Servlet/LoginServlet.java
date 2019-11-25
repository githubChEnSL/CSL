package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.regulator;
import jdbc.DatabaseRegulator;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 管理员登陆
	public static Map<String, Object> RegulatorLogin(String RegulatorNum, String Password) {
		boolean flag = false;
		Map<String, Object> map=new HashMap<String, Object>();
		DatabaseRegulator database = new DatabaseRegulator();
		Integer roleid=-1;
		try {
			regulator getObject = database.GetRegulatorForId(RegulatorNum);
			roleid=Integer.parseInt(getObject.getRegulatorRoleId());
			if(Integer.parseInt(getObject.getRegulatorRoleId())==3) {
				flag=false;
			}else {
				if (getObject.getRegulatorName() != null) {
					if (getObject.getPassword().equals(Password)) {
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
		map.put("roleid", roleid);
		database.CloseDatabase();
		
		return map;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String error = "";
		boolean flag=(boolean) RegulatorLogin(name, password).get("flag");
		System.err.println(flag);
		Integer id=(Integer) RegulatorLogin(name, password).get("roleid");
		if (flag) {
			System.out.println("登陆请求----登陆成功");
			System.err.println(id);
			request.getSession().setAttribute("roleId", id);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("error", "");
			response.sendRedirect("index.jsp");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			System.out.println("登陆请求----登陆失败");
			error = "账号或密码错误,或您无权登陆系统";
			request.getSession().setAttribute("error", error);
			// response.sendRedirect("login.jsp");
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
