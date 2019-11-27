package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * OutloginFilter过滤器 实现退出登录
 * 
 * @author chenshaolei 2019年11月27日 上午11:52:23
 */
public class OutloginFilter implements Filter {

	// 定义Log4j日志
	private static Logger logger = LogManager.getLogger(Class.class);

	/**
	 * 重写doFilter,完成退出登录
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("加载Exit...退出登录");
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;// 获取request对象
		HttpSession session = req.getSession();
		session.invalidate();// 清空session域
		//System.out.println("加载Exit...");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
