package com.tcps.java.service;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.sword.wechat4j.WechatSupport;

import com.tcps.java.common.util.OAuthPermisson;
import com.tcps.java.common.util.TemplateMsgConstructor;


public class TcpsWechat extends WechatSupport {
	WechatService wechatService;
	HttpServletRequest request;
	public TcpsWechat(HttpServletRequest request){
		super (request);
		this.request = request;
	}
	public void getService(){
			HttpSession session = this.request.getSession();
			ServletContext servletContext = session.getServletContext();
		 	ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		 	this.wechatService = (WechatService)appctx.getBean("wechatservice");
	}
	@Override
	protected void click() {
		// TODO Auto-generated method stub
		getService();
		//个人信息在注册表中的查询
		if(this.wechatRequest.getEventKey().equals("11")){
				try {
					Map<String,Object> user = this.wechatService.queryUser(this.wechatRequest.getFromUserName());
					if(!user.isEmpty()){
					/*	String cardid ="";
						int idNo = Integer.parseInt(user.get("ID_NO").toString());
						while(idNo > 0){
							cardid = cardid+" \n"+ user.get(("IC_ID"+idNo).toString());
							idNo--;
						}
						TemplateMsgConstructor msgConstructor = new TemplateMsgConstructor();
						msgConstructor.showUser(user.get("WX_ID").toString(), cardid, user.get("CITY").toString());*/
						StringBuffer responseTextBuffer = new StringBuffer();
						responseTextBuffer.append("查看个人信息 \n");
						responseTextBuffer.append("尊敬的用户：您好 \n");
						responseTextBuffer.append("用户ID:"+user.get("WX_ID")+"\n");
						responseTextBuffer.append("卡号:");
						int count = Integer.parseInt(user.get("ID_NO").toString());
						while(count>0){
						responseTextBuffer.append(user.get("IC_ID"+count)+"\n");
						count--;
						}
						responseTextBuffer.append("城市:"+user.get("CITY")+"\n");
						responseTextBuffer.append("感谢您的使用!");
						responseText(responseTextBuffer.toString());
					}
					else{
						String url = "http://www.yanwenxiong.cn/TcpsWechat/Redirect?t=1";
						OAuthPermisson temp = new OAuthPermisson();
						responseText("尊敬的用户您好，您尚未绑定卡号，请点击链接进行绑定：\n"+temp.urlTransform(url));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					responseText("尊敬的用户：您好。\n 公众号服务维护中，请联系客服人员进行确认。");
				}
	
		}
		//交易表中的余额数据查询
		else if(this.wechatRequest.getEventKey().equals("12")){
			//数据库中查询到该绑定记录
			if(true){
				//To do 返回个人卡号信息，关联标准的支付账号数据库，进行查询余额
				responseText("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1eba1bcac0c1deca&redirect_uri="
		        		+ "http%3A%2F%2Fwww.yanwenxiong.cn%2FTcpsWechat%2FRedirect%3Ft%3D2"
		        		+ "&response_type=code&scope=snsapi_base&state=a#wechat_redirect");
			}
		}
		
		else if(this.wechatRequest.getEventKey().equals("31")){
			responseText("客服电话：1234567 \n 客服微信：1234567 \n 客服QQ：1234567 \n");
		}
		else if(this.wechatRequest.getEventKey().equals("32")){
			responseText("请确保绑定的卡号无误，否则本公司将不负任何法律责任。");
		}
	}

	@Override
	protected void kfCloseSession() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void kfCreateSession() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void kfSwitchSession() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void location() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void locationSelect() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onImage() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLink() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLocation() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onShortVideo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onText() {
		// TODO Auto-generated method stub
		String requestText = this.wechatRequest.getContent();
		if(requestText.contains("1") || requestText.contains("2")){
			this.wechatRequest.setEventKey("11");
			click();
		}
		else if(requestText.contains("3")){
			//返回图文消息，并关联经Oauth2认证的地址，点击图文消息访问订单生成
		}
		else if(requestText.contains("4")){
			//返回图文消息，并关联经Oauth2认证的地址，点击图文消息访问订单生成
		}
		else if(requestText.contains("5")){
			this.wechatRequest.setEventKey("31");
			click();
		}
		else if(requestText.contains("6")){
			this.wechatRequest.setEventKey("32");
			click();
		}
		else{
		StringBuffer responseText = new StringBuffer();
		responseText.append("尊敬的用户：您好，欢迎使用通卡公司微信服务").append("\n");
		responseText.append("(1)卡号绑定").append("\n");
		responseText.append("(2)查询个人信息").append("\n");
		responseText.append("(3)订单创建").append("\n");
		responseText.append("(4)订单查询").append("\n");
		responseText.append("(5)客服信息").append("\n");
		responseText.append("(6)注意事项").append("\n");
		responseText(responseText.toString());
		}
	}

	@Override
	protected void onUnknown() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onVideo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onVoice() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void picPhotoOrAlbum() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void picSysPhoto() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void picWeixin() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void scan() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void scanCodePush() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void scanCodeWaitMsg() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void subscribe() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void templateMsgCallback() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void unSubscribe() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void view() {
		// TODO Auto-generated method stub

	}

}
