package com.tcps.java.servlet;

import java.io.IOException;

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
import com.tcps.pay.service.PayService;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updateOrder")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WechatService wechatService;
	PayService  payService;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void getService(HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
	 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	 	this.wechatService = (WechatService)appctx.getBean("wechatservice");
	 	this.payService = (PayService) appctx.getBean("Pay");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getService(request);
		String openid = request.getParameter("openid");
		String orderNo = request.getParameter("orderNo");
		String state = this.wechatService.updateOrder(openid, orderNo);
		this.payService.updatePayStatus(orderNo);
		response.getWriter().write(state);
	}

}
