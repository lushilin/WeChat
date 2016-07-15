package com.tcps.java.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tcps.java.common.model.Order;
import com.tcps.java.service.WechatService;

/**
 * Servlet implementation class RechargeServlet
 */
@WebServlet("/rechargedo")
public class RechargeServlet extends HttpServlet {
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
    public RechargeServlet() {
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		getService(request);
		// 防止后退出现错误
		if(request.getSession().getAttribute("backTag") != null){
		request.getSession().removeAttribute("backTag");
		Order order = saveNewOrder(request);
		wechatService.saveOrder(order);
		request.getSession().setAttribute("product_id",request.getParameter("product_id"));
		request.getRequestDispatcher("pay1.jsp").forward(request, response);
		}
	}
	public Order saveNewOrder(HttpServletRequest request){
		// 10位时间+1位类型（奇数）+5位序列
		String orderNo = getOrderNo();
		request.getSession().setAttribute("orderNo", orderNo);
		// 创建订单时间
		String orderTime = getTime();
		request.getSession().setAttribute("orderTime", orderTime);
		// 订单状态 0：未付款 1：已付款 2:已完成充值 9：取消订单 7：已退款
		int is_Ok = 0;
		// 充值金额
		int orderMoney = Integer.parseInt(request.getParameter("total_price"));
		// 手续费
		double poundageMoney = 0.5;
		// 总金额
		double sumMoney = orderMoney + poundageMoney;
		request.getSession().setAttribute("sumMoney", sumMoney);
		// 卡号 TODO delete
		String cardId = request.getParameter("card_number");
	
		// 用户 用session比较好？
		String userId = request.getSession().getAttribute("openid").toString();
		// 0：未结转 1：结转
		int balanceFlag = 0;
		// 支付类型
		int payType = 0;
		// 0：未到警戒风控 1：警戒风控
		int warnType = 0;
		return new Order(orderNo ,orderTime ,is_Ok ,orderMoney ,poundageMoney ,sumMoney ,cardId ,userId ,balanceFlag ,payType ,warnType);
	}
	
	public String getTime(){
		String time;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		time = f.format(date);
		return time;
	}
	
	public String getOrderNo(){
		StringBuffer orderNo = new StringBuffer();
		SimpleDateFormat f = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		orderNo.append(f.format(date));
		orderNo.append("1");
		orderNo.append(this.wechatService.getSerieNo());
		return orderNo.toString();
	}
}
