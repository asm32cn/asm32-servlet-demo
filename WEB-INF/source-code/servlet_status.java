import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.text.*;

public class servlet_status extends HttpServlet {
	public String m_strAppName = "servlet_status";
	public String m_strAppName_cn = "servlet-status-demo-1";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
		response.setLocale(Locale.SIMPLIFIED_CHINESE);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>" + m_strAppName_cn + "</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
		out.println("</head>");
		out.println("<style> body{ background-color:#a5cbf7; }</style>");
		out.println("<body>");

		out.println("<h1>" + m_strAppName_cn + "</h1>");

		String[][] status = {
			{"request.getContextPath()", request.getContextPath()},
			{"request.getScheme()", request.getScheme()},
			{"request.getProtocol()", request.getProtocol()},
			{"request.getServerName()", request.getServerName()},
			{"request.getServerPort()", toString(request.getServerPort()) },
			{"request.getLocalAddr()", request.getLocalAddr()},
			{"request.getLocalName()", request.getLocalName()},
			{"request.getLocalPort() ", toString(request.getLocalPort()) },
			{"request.getMethod()", request.getMethod()},
			{"request.getRequestURI()", request.getRequestURI()},
			{"request.getRequestURL() ", request.getRequestURL().toString() },
			{"request.getServletPath()", request.getServletPath()},
			{"request.getServletContext().getRealPath(\"/\")(从servlet3.0开始)", request.getServletContext().getRealPath("/")},
			{"request.getSession().getServletContext().getRealPath(\"/\")(从servlet2.3开始)", request.getSession().getServletContext().getRealPath("/")},
			{"--------客户端信息--------", ""},
			{"request.getRemoteAddr()", request.getRemoteAddr()},
			{"request.getRemoteHost()", request.getRemoteHost()},
			{"request.getRemotePort()", toString(request.getRemotePort()) }
		};

		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\" borderColor=\"#666\" cellspacing=\"0\" cellpadding=\"5\" style=\"border-collapse:collapse;\">\n");
		for(int i = 0, l = status.length; i < l; i++){
			sb.append("\t<tr>\n");
			sb.append("\t\t<td>").append( status[i][0] ).append("</td>\n");
			sb.append("\t\t<td>").append( status[i][1] ).append("</td>\n");
			sb.append("\t</tr>\n");
		}
		sb.append("</table>\n");

		out.println( sb.toString() );

		out.println("<br />");

		out.println( request.getRealPath("/") );

		out.println( PA_doListFiles() );

		out.println("</body>");
		out.println("</html>");
	}

	private String toString(int i){
		return String.valueOf(i);
	}

	private String URLEncoder(String s){
		if( s != null ){
			try{
				return java.net.URLEncoder.encode(s, "utf-8");
			}catch(Exception ex){}
		}
		return null;
	}

	private String PA_doListFiles(){
		StringBuilder sb = new StringBuilder();
		String m_strFolder__upload = this.getServletConfig().getServletContext().getRealPath("/") + java.io.File.separator;
		// String strFolder = ".";
		File folder = new File(m_strFolder__upload);
		File[] files = folder.listFiles();
		sb.append("\n<pre>");
		for(File f : files){
			sb.append( sdf.format(new Date(f.lastModified()) ) );
			sb.append(" ");
			boolean isDirectory = f.isDirectory();
			sb.append(isDirectory ? 'd' : '-');
			sb.append(f.canRead() ? 'r' : '-');
			sb.append(f.canWrite() ? 'w' : '-');
			sb.append(f.canExecute()? 'x' : '-');
			sb.append(" ");
			if(isDirectory){
				sb.append("        &lt;dir&gt;");
			}else{
				sb.append(String.format("%13s", NumberFormat.getInstance().format(f.length())));
			}
			sb.append(" ");
			String fileName = f.getName();
			// sb.append("<a href=\"./").append( URLEncoder(fileName) ).append("\">").append(fileName).append("</a>");
			sb.append(fileName);
			sb.append("\n");
		}
		sb.append("</pre>\n\n");
		return sb.toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
		response.setLocale(Locale.SIMPLIFIED_CHINESE);

		doGet(request, response);
	}

}