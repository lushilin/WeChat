package com.tcps.pay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcps.pay.model.oauth2.OAuth2AccessToken;
import com.tcps.pay.service.OAuth2Service;

/**
 * Title: OAuth2Servlet
 * Description: 这个servlet主要是用来处理用户是否OAuth2.0授权
 * Company:天津通卡
 * @author yanwenxiong
 * @date 2016年5月20日
 */
@WebServlet("/oauth2")
public class OAuth2Servlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public OAuth2Servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String code = request.getParameter("code");

		// 如果用户同意授权，获取到了code，code不为空
		if (null != code) {
			// 获取access_token
			OAuth2AccessToken oAuth2AccessToken = OAuth2Service.getOAuth2AccessToken(code);
			// TODO 如果无法获取授权怎么办。。。
			if (oAuth2AccessToken != null){
				String openid = oAuth2AccessToken.getOpenid();
				session.setAttribute("openid", openid);

				request.getRequestDispatcher("/pages/goods.jsp").forward(
						request, response);
				return;
			}

		}
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=wx7e244b5ad1f1e1b9&"
				+ "redirect_uri=http%3A%2F%2Fwww.yanwenxiong.cn%2FWechat%2Fservlet%2FOAuth2Servlet&"
				+ "response_type=code&"
				+ "scope=snsapi_userinfo&"
				+ "state=STATE#wechat_redirect");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
