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

/**
 * Servlet implementation class DeleteUnpaidServlet
 */
@WebServlet("/deleteUnpaid")
public class DeleteUnpaidServlet extends HttpServlet {
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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUnpaidServlet() {
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
		String orderNo = (String)request.getSession().getAttribute("orderNo");
		String openid = (String)request.getSession().getAttribute("openid");
		if(orderNo != null){
			this.wechatService.deleteUnpaidOrder(openid);
			request.getRequestDispatcher("query").forward(request, response);
		}
		else {
			response.getWriter().write("找不到订单号，网络出现异常");
		}
	}

}
