package com.tcps.java.common.model;
import org.apache.log4j.Logger;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.message.TemplateMsg;
import org.sword.wechat4j.message.template.TemplateMsgBody;
import org.sword.wechat4j.message.template.TemplateMsgData;
import org.sword.wechat4j.token.TokenProxy;

import com.alibaba.fastjson.JSONObject;

public class NewTemplateMsg extends TemplateMsg {
	private static Logger logger = Logger.getLogger(TemplateMsg.class);
	 private String accessToken;
	public NewTemplateMsg(){
		accessToken=TokenProxy.accessToken();
	}
	public String send(TemplateMsgBody postData)
	   {
		logger.info("send template message");
	     String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + this.accessToken;
	     JSONObject json = new JSONObject();
	     json.put("touser", postData.getTouser());
	     json.put("template_id", postData.getTemplateId());
	     json.put("url", postData.getUrl());
	     JSONObject jsonData = new JSONObject();
	     for (TemplateMsgData data : postData.getData()) {
	       JSONObject keynote = new JSONObject();
	       keynote.put("value", data.getValue());
	       keynote.put("color", data.getColor());
	       jsonData.put(data.getName(), keynote);
	    }
	     json.put("data", jsonData);
	     String data = json.toJSONString();
	     String result = HttpUtils.post(url, data);
	     logger.info("post result:" + result);
	     JSONObject resultJson = JSONObject.parseObject(result);
	     if (resultJson.getString("errcode").equals("0")) {
	       return resultJson.getString("msgid");
	     }
	     logger.error("send template message error:" + resultJson.getString("errmsg"));
	    return null;
	  }
}
