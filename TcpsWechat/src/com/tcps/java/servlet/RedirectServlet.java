package com.tcps.java.servlet;

import java.io.IOException;
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
import org.sword.lang.HttpUtils;

import com.alibaba.fastjson.JSONObject;
import com.tcps.java.service.WechatService;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/Redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    WechatService wechatservice;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void getService(HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
	 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	 	this.wechatservice = (WechatService)appctx.getBean("wechatservice");
	}
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		getService(request);
		String code = request.getParameter("code");
		String t = request.getParameter("t").toString();
		String url =  "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=wx7e244b5ad1f1e1b9"
				+ "&secret=2dce0b92d741112f686dc0a9fef3b193"
				+ "&code="+code
				+ "&grant_type=authorization_code"; 
		String json = HttpUtils.get(url);
		JSONObject jsonObject =(JSONObject) JSONObject.parse(json);
		
		if(jsonObject.getString("openid") == null && t.equals("1"))
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
	        		+ "appid=wx7e244b5ad1f1e1b9&redirect_uri="
	        		+ "http%3A%2F%2Fwww.yanwenxiong.cn%2FTcpsWechat%2FRedirect%3Ft%3D1"
	        		+ "&response_type=code&scope=snsapi_base&state=a#wechat_redirect");
		else if(jsonObject.getString("openid") == null && t.equals("2"))
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
	        		+ "appid=wx7e244b5ad1f1e1b9&redirect_uri="
	        		+ "http%3A%2F%2Fwww.yanwenxiong.cn%2FTcpsWechat%2FRedirect%3Ft%3D2"
	        		+ "&response_type=code&scope=snsapi_base&state=a#wechat_redirect");
		else if(jsonObject.getString("openid") == null && t.equals("3"))
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
	        		+ "appid=wx7e244b5ad1f1e1b9&redirect_uri="
	        		+ "http%3A%2F%2Fwww.yanwenxiong.cn%2FTcpsWechat%2FRedirect%3Ft%3D3"
	        		+ "&response_type=code&scope=snsapi_base&state=a#wechat_redirect");
		else if(jsonObject.getString("openid") != null){
			String openid = jsonObject.getString("openid").toString();
			request.getSession().setAttribute("openid", openid);
			if(t.equals("1"))
			    request.getRequestDispatcher("bind-test1.jsp").forward(request, response);		
			if(t.equals("2")){
				Map<String,Object> map = (Map<String,Object>) this.wechatservice.queryUnpaidOrder(openid);
				if(map.isEmpty()){
					Map<String,Object> user = (Map<String,Object>) this.wechatservice.queryUser(openid);
					if(!user.isEmpty())
						request.setAttribute("user", user);
					request.getRequestDispatcher("recharge.jsp").forward(request, response);
				}
					else{
					HttpSession session = request.getSession();
					session.setAttribute("orderNo", map.get("ORDER_NO"));
					session.setAttribute("sumMoney", map.get("SUM_MONEY"));
					session.setAttribute("orderTime", map.get("ORDER_TIME"));
					request.getRequestDispatcher("forUnpaid.jsp").forward(request, response);
				}
				}
			if(t.equals("3")){
				request.getRequestDispatcher("query").forward(request,response);
			}
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
