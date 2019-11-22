package Servlet;

import java.io.IOException;

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
	public static boolean RegulatorLogin(String RegulatorNum, String Password) {
		boolean flag = false;
		DatabaseRegulator database = new DatabaseRegulator();
		try {
			regulator getObject = database.GetRegulatorForId(RegulatorNum);
			if(getObject.getRegulatorRoleId()=="3") {
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
		database.CloseDatabase();
		return flag;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String LoginMsg = "";
		if (RegulatorLogin(name, password)) {
			System.out.println("登陆请求----登陆成功");
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("LoginMsg", LoginMsg);
			response.sendRedirect("index.jsp");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			System.out.println("登陆请求----登陆失败");
			LoginMsg = "账号或密码错误,或您无权登陆系统";
			request.getSession().setAttribute("LoginMsg", LoginMsg);
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
