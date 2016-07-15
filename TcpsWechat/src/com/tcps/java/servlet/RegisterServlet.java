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

import com.tcps.java.common.model.User;
import com.tcps.java.service.WechatService;

/**
 * Servlet implementation class addServlet
 */
@WebServlet("/add")
public class RegisterServlet extends HttpServlet {
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
    public RegisterServlet() {
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


		String openId = request.getSession().getAttribute("openid").toString();

		String cardId =request.getParameter("card_id");
		String cityCode = request.getParameter("city");
		
		getService(request);
		// TO-DO 绑定的时候要查询城市和卡号的信息是否符合或者卡号符合格式，在抛出异常的时候判断哪种异常被抛出
		String state = wechatService.saveRegister(new User(openId,cardId,cityCode));
		response.getWriter().write(state);
			
	}

}
