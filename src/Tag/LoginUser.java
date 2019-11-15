package Tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoginUser extends SimpleTagSupport{
	public void doTag() {
		JspWriter out=getJspContext().getOut();
		PageContext pageContext=(PageContext)getJspContext();
		HttpSession session= pageContext.getSession();
		String name = (String)session.getAttribute("name");
		if(name!=null) {
			try {
				out.println("欢迎您的登陆,"+name);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("哼，出错啦");
			}
		}else {
			try {
				out.println("请重新登陆");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
