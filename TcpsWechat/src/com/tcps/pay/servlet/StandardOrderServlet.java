package com.tcps.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcps.pay.model.order.OrderReqData;

@WebServlet("/stdOrder")
public class StandardOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor of the object.
	 */
	public StandardOrderServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return void
	 * 业务流程介绍：接收到用户调用微信统一下单的请求，进行下单操作
	 * 1、先从请求中提取出此下单操作的详细信息
	 * 2、向订单表中插入一条新的订单，状态为未支付。
	 * 3、前往支付页面
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、先取出信息。
		String product_id = request.getParameter("product_id");
		String out_trade_no = OrderReqData.generateOutTradeNo();
		//2、向订单表中插入一条新的订单，状态为未支付。
		//TODO 梁俊鹏的工作。

		
		//3、前往支付页面
		response.sendRedirect("/Wechat/pages/pay.jsp?product_id=" + product_id + 
				"&out_trade_no=" + out_trade_no);
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
