package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * CharacterEncodingFilter过滤器 实现字符编码的设置
 * 
 * @author chenshaolei 2019年11月27日 上午11:50:49
 */
public class CharacterEncodingFilter implements Filter {

	private static String encoding; // 定义变量接受初始化的值

	/**
	 * 重写doFilter，完成字符编码的设置
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			// System.out.println("设置字符编码--成功");
		} catch (Exception e) {
			System.out.println("设置字符编码--失败");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
