package com.tcps.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.model.goods.Goods;
import com.tcps.pay.model.order.OrderReqData;
import com.tcps.pay.model.order.OrderRespData;
import com.tcps.pay.service.PayService;


public class PaymentServlet extends HttpServlet {

	public PaymentServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * @param request httpRequest
	 * @param response httpResponse
	 * 处理业务的逻辑：对于来自支付页面的请求，先获得商户自定义的订单号
	 * 然后，根据订单号准备统一下单的数据，向微信服务器进行下单。
	 * 对于回写的信息：0代表网络情况异常，没有正常接收到参数；
	 * 1代表未能在数据库里面查到对应的订单信息，无法完成付款，可能由于连接数据库错误或者网络情况不好。
	 * 2代表微信支付发生错误，在日志中查看错误即可。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String out_trade_no = request.getParameter("out_trade_no");
		String product_id = request.getParameter("product_id");
		PrintWriter writer = response.getWriter();
		
		if(out_trade_no == null || "".equals(out_trade_no) || "null".equals(out_trade_no)
			|| product_id == null || "".equals(product_id) || "null".equals(product_id)){
			writer.write("0");
			return;
		}
		else{
			try {
				//查询数据库，查到product_id对应的商品信息和out_trade_no对应的订单信息，拼接OrderReqData，向微信服务器准备统一下单
			} catch (Exception e) {
				// TODO: handle exception
				writer.write("1");
				return;
			}
			//模拟数据：表示现在已经查到了对应的数据，并且组织OrderReqData。
			HttpSession session = request.getSession();
			ServletContext servletContext = session.getServletContext();
		 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		 	PayService payService = (PayService)appctx.getBean("Pay");
		 	
			Goods good = new Goods(product_id);
			String ip = request.getRemoteAddr();
			String notify_url = "http://www.yanwenxiong.cn/Wechat/servlet/NotifyServlet";
			String openid = (String) session.getAttribute("openid");
			//openid = "oCkaPwbfdD-XIOqXl2JVK2AxxO4M";
			if(openid == null){
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
						+ "appid=wx7e244b5ad1f1e1b9&"
						+ "redirect_uri=http%3A%2F%2Fwww.yanwenxiong.cn%2FWechat%2Fservlet%2FOAuth2Servlet&"
						+ "response_type=code&"
						+ "scope=snsapi_userinfo&"
						+ "state=STATE#wechat_redirect");
				//重新去获取openid进行下单操作。
				return;
			}
			OrderReqData data = new OrderReqData(good, 1, openid, null,out_trade_no, ip);
			
			//向支付表插入数据
			String return_url = "http://www.yanwenxiong.cn/Wechat/pages/success.jsp";
		 	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		 	String create_time = sdf.format(new Date());
		 	//TODO 有一个比较大的问题？就是重复点击怎么办？
		 	try {
				payService.createOrder(data.getOut_trade_no(), "", data.getTotal_fee(), 
						return_url, data.getNotify_url(), "", "", create_time, 0, "", 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//调用微信官方提供的统一下单接口，获得返回的数据
			OrderRespData respData = payService.run(data);
			//根据返回的数据进行下一步的操作
			if(respData != null){
				if("SUCCESS".equals(respData.getResult_code())){
					// 支付业务处理成功了
					String appId = Configure.getAppid();
					String timestamp = String.valueOf(PayUtill.getTimeStamp());
					String nonceStr = PayUtill.randomString(32);
					String packages = "prepay_id=" + respData.getPrepay_id();
					String signType = "MD5";
					HashMap<String, String> payMap = new HashMap<>();
					payMap.put("appId", appId);
					payMap.put("timeStamp", timestamp);
					payMap.put("nonceStr", nonceStr);
					payMap.put("package", packages);
					payMap.put("signType", signType);
					String paySign = Signature.getSign(payMap);
					payMap.put("paySign", paySign);
					String respString = XMLHandler.mapToXML(payMap);
					writer.write(respString);
					return;
				}
			}
			writer.write("2");
			return;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
