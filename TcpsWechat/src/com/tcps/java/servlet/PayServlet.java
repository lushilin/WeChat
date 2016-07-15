package com.tcps.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.tcps.java.service.WechatService;
import com.tcps.pay.common.Configure;
import com.tcps.pay.common.PayUtill;
import com.tcps.pay.common.Signature;
import com.tcps.pay.common.XMLHandler;
import com.tcps.pay.model.goods.Goods;
import com.tcps.pay.model.order.OrderReqData;
import com.tcps.pay.model.order.OrderRespData;
import com.tcps.pay.service.PayService;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/payment")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WechatService wechatService;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void getService(HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
	 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	 	this.wechatService = (WechatService)appctx.getBean("wechatservice");
	}
 
    public PayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 先从上一个页面获取到订单号orderNo,下单时间orderNo,商品id:product_id,然后开始准备统一下单的数据
	 * 然后想支付表中插入一条数据，记录下将要发生的支付的情况。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getService(request);
		//1 先从上一个页面获取到订单号orderNo,下单时间orderNo,商品id:product_id,然后开始准备统一下单的数据
		String openid = request.getSession().getAttribute("openid").toString();
		String orderNo = request.getSession().getAttribute("orderNo").toString();
		String orderTime = request.getSession().getAttribute("orderTime").toString();
		String product_id  = request.getSession().getAttribute("product_id").toString();
		
		PrintWriter writer = response.getWriter();
		if(orderNo == null || "".equals(orderNo) || "null".equals(orderNo)
				|| product_id == null || "".equals(product_id) || "null".equals(product_id)){
				writer.write("0");
				return;
		}
		else{
			HttpSession session = request.getSession();
			ServletContext servletContext = session.getServletContext();
		 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		 	PayService payService = (PayService)appctx.getBean("Pay");
		 	
			Goods good = new Goods(product_id);
			String ip = request.getRemoteAddr();
			if(openid == null){
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
						+ "appid=wx7e244b5ad1f1e1b9&"
						+ "redirect_uri=http%3A%2F%2Fwww.yanwenxiong.cn%2FTcpsWechat%2Foauth2&"
						+ "response_type=code&"
						+ "scope=snsapi_userinfo&"
						+ "state=STATE#wechat_redirect");
				//重新去获取openid进行下单操作。
				return;
			}
			OrderReqData data = new OrderReqData(good, 1, openid, null,orderNo, ip);
			
			//2 然后想支付表中插入一条数据，记录下将要发生的支付的情况。
			String return_url = "http://www.yanwenxiong.cn/Wechat/pages/success.jsp";
		 	//TODO 有一个比较大的问题？就是重复点击怎么办？
		 	try {
				payService.createOrder(data.getOut_trade_no(), "", data.getTotal_fee(), 
						return_url, data.getNotify_url(), "", "", orderTime, 0, "", 0);
			} catch (Exception e) {
				//插入数据库发生异常，用log记录。
				
			}
			
			//3 调用微信官方提供的统一下单接口，获得返回的数据
			OrderRespData respData = payService.run(data);
			//4 根据返回的数据进行下一步的操作
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
			writer.write("1");
			return;
		}
	}

}
