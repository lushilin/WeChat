package com.tcps.pay.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.tcps.pay.common.HttpClientUtil;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.service.NotifyResultService;

@WebServlet("/notify")
public class NotifyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public NotifyServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
	 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	 	NotifyResultService notifyResultService = (NotifyResultService)appctx.getBean("Notify");
	 	
	 	InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
	 	BufferedReader br = new BufferedReader(inputStreamReader);
	 	StringBuffer sb = new StringBuffer();
	 	String text = null;
	 	while((text=br.readLine()) != null){
	 		sb.append(text);
	 	}
	 	String notifyResp = sb.toString();
	 	System.out.println(notifyResp);
	 	
	 	if(notifyResp != null || "".equals(notifyResp)){
	 		Map<String, String> map = XMLHandler.XMLStrToMap(notifyResp);
	 		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	 		int status = notifyResultService.notifyResult(map);
	 		switch(status){
	 			//成功确认，返回正确信息。并且给前台发一条消息。
		 		case 0:{
		 			//这个是做一个主动回复消息。
		 			String content = "您在" + sdf.format(new Date()) + "成功支付了订单" + map.get("out_trade_no") + "\n"
		 					+ "支付总金额为：" + map.get("total_fee") + "\n"
		 					+ "如果您对此笔交易有问题，请您进入帮助中心，联系我们，我们竭诚为您服务！";
		 			StringBuffer sb1 = new StringBuffer();
		 			Date date = new Date();
		 			sb1.append("<xml><ToUserName><![CDATA[");
		 			sb1.append(map.get("openid"));
		 			sb1.append("]]></ToUserName><FromUserName><![CDATA[");
		 			sb1.append("gh_4250831d9474");
		 			sb1.append("]]></FromUserName><CreateTime>");
		 			sb1.append(date.getTime());
		 			sb1.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		 			sb1.append(content);
		 			sb1.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
		 			
		 			
		 			String respStr = notifyResultService.respXMLStr;
		 			PrintWriter writer = response.getWriter();
		 			writer.write(respStr);
		 			writer.flush();
		 			writer.close();
		 			break;
		 		}
		 		//已经确认过订单，无需任何操作。
		 		case 1 :{
		 			break;
		 		}
		 		//订单号不存在或者已经关闭了订单。无需进行任何操作。
		 		case 2 :{
		 			break;
		 		}
		 		//签名错误，通知微信服务器信息。
		 		case 3:{
		 			String respStr = notifyResultService.respXMLStr;
		 			respStr = respStr.replace("SUCCESS", "FAIL");
		 			respStr = respStr.replace("OK", "签名失败");
		 			PrintWriter writer = response.getWriter();
		 			writer.write(respStr);
		 			writer.flush();
		 			writer.close();
		 			break;
		 		}
		 		case 4:{
		 			String respStr = notifyResultService.respXMLStr;
		 			respStr = respStr.replace("SUCCESS", "FAIL");
		 			respStr = respStr.replace("OK", "收到的通知为空");
		 			PrintWriter writer = response.getWriter();
		 			writer.write(respStr);
		 			writer.flush();
		 			writer.close();
		 			break;
		 		}
	 		}
	 	}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
