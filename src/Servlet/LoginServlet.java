package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RegulatorController;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String LoginMsg="";
		if(RegulatorController.RegulatorLogin(name, password)) {
				System.out.println("登陆请求----登陆成功");
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("LoginMsg", LoginMsg);
				response.sendRedirect("index.jsp");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
				System.out.println("登陆请求----登陆失败");
				LoginMsg="账号或密码错误";
				request.getSession().setAttribute("LoginMsg",LoginMsg);
				 //response.sendRedirect("login.jsp");
				request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
