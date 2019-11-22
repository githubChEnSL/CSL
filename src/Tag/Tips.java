package Tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Tips extends SimpleTagSupport{
	public void doTag() {
		JspWriter out=getJspContext().getOut();
		PageContext pageContext=(PageContext)getJspContext();
		HttpSession session= pageContext.getSession();
		String LoginMsg = (String)session.getAttribute("LoginMsg");
		if(LoginMsg!=null) {
			try {
				out.println("<p style='color:red'>"+LoginMsg+"<p>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			;
		}
		
	}
}
