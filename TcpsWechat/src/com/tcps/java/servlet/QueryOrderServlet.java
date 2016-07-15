package com.tcps.java.servlet;

import java.io.IOException;
import java.util.List;
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

import com.tcps.java.service.WechatService;

/**
 * Servlet implementation class QueryOrderServlet
 */
@WebServlet("/query")
public class QueryOrderServlet extends HttpServlet {
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
	
    public QueryOrderServlet() {
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
		
		request.getSession().removeAttribute("okList");
		request.getSession().removeAttribute("unpaidOrder");
		getService(request);
		if(request.getSession().getAttribute("openid") != null){
		String openid = request.getSession().getAttribute("openid").toString();
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> okOrder =(List<Map<String,Object>>) this.wechatService.queryOkOrder(openid);
		if(okOrder.size() > 0)
			request.getSession().setAttribute("okList", okOrder);	
		Map<String,Object> unpaidOrder = (Map<String,Object>) this.wechatService.queryUnpaidOrder(openid);
		if(!unpaidOrder.isEmpty())
			request.getSession().setAttribute("unpaidOrder", unpaidOrder);
	    }
		request.getRequestDispatcher("showOrder-test1.jsp").forward(request, response);
	}

}
