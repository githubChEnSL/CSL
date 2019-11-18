package Tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ErrorMessageTag extends SimpleTagSupport{

		public void doTag() {
			JspWriter out=getJspContext().getOut();
			PageContext pageContext=(PageContext)getJspContext();
			HttpSession session= pageContext.getSession();
			String name = (String)session.getAttribute("name");
			String error=(String) session.getAttribute("error");
			if(name==null) {
				if(error!=null) {
					try {
						out.println("<p style='color:red;'>"+error+"</p>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					;
				}
			}else {
				if(error!=null) {
					try {
						out.println("<p style='color:red;'>"+error+"</p>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					;
				}
			}
			
		}
}
