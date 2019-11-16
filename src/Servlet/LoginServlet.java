package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.regulator;
import entity.user;
import jdbc.Database;
import jdbc.DatabaseRegulator;
import jdbc.DatabaseUser;

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
		DatabaseRegulator database=new DatabaseRegulator();
		try {
		   regulator getObject=database.GetRegulatorForId(name);
		   if(getObject.getRegulatorName()!=null) {
			   if(getObject.getPassword().equals(password)) {
				   System.out.println("登陆请求----登陆成功");
				   request.getSession().setAttribute("name", name);
					response.sendRedirect("index.jsp");
					//request.getRequestDispatcher("index.jsp").forward(request, response);
			   }else {
				   System.out.println("登陆请求----登陆失败");
				   response.sendRedirect("error.jsp");
				   request.getSession().setAttribute("error", "账号或密码错误，点击返回登陆");
			   }
		   }else {
			   System.out.println("登陆请求----登陆失败");
			   response.sendRedirect("error.jsp");
			   request.getSession().setAttribute("error", "账号或密码错误，点击返回登陆");
		   }
			
		} catch (Exception e) {
			 System.out.println("登陆请求----登陆异常");
			   response.sendRedirect("error.jsp");
			   request.getSession().setAttribute("error", "登陆异常，点击返回登陆");
		}
		database.CloseDatabase();
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
