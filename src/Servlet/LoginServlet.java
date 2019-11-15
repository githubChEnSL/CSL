package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(name.equals("chenshaolei") && password.equals("172056236")) {
			System.out.println("鐧婚檰璇锋眰----鐧婚檰鎴愬姛");
			request.getSession().setAttribute("name", name);
			
			response.sendRedirect("index.jsp");
			//request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			System.out.println("鐧婚檰璇锋眰----鐧婚檰澶辫触");
			request.getSession().setAttribute("error", "瀵嗙爜閿欒锛岀偣鍔犺繑鍥炵櫥闄�");
			response.sendRedirect("error.jsp");
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
